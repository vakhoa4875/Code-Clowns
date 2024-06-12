package codeclowns.planny.planny.service.impl;

import codeclowns.planny.planny.constant.BasicApiConstant;
import codeclowns.planny.planny.constant.LoginStatus;
import codeclowns.planny.planny.constant.RegisterStatus;
import codeclowns.planny.planny.data.dto.AccountDto;
import codeclowns.planny.planny.data.entity.AccountE;
import codeclowns.planny.planny.data.mgt.ResponseObject;
import codeclowns.planny.planny.repository.AccountRepository;
import codeclowns.planny.planny.service.AccountService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession session;
     private final JavaMailSender mailSender;

    @Override
    public LoginStatus login(AccountDto accountDto) {
        var account = accountRepository.findByEmailOrUsername(accountDto.getEmail(), accountDto.getUsername());
        if (account == null) return LoginStatus.NOT_EXISTED;
        if (passwordEncoder.matches(accountDto.getPassword(), account.getPassword())) {
            LoginStatus.SUCCEED.setStateDescription("Chào mừng trở lại, " + account.getUsername() + "!");
            session.setAttribute("currentAccount", account);
            return LoginStatus.SUCCEED;
        }
        return LoginStatus.FAILED_PASSWORD;
    }
    @Override
    public RegisterStatus register(AccountDto accountDto) {
        if (accountRepository.findByEmailOrUsername(accountDto.getEmail(), accountDto.getUsername()) != null) {
            return RegisterStatus.ACCOUNT_EXISTED;
        }
        savePendingAccount(accountDto);
        return RegisterStatus.PENDING;
    }
    @Override
    public Optional<AccountE> findAccountByUsername(String username) {
        var account = accountRepository.findAccountByUsernameOrEmailAndIsEnabledTrue(username, username);
        return Optional.of(account);
    }
    @Override
    public void savePendingAccount(AccountDto accountDto) {
          session.setAttribute("PENDING_ACCOUNT_" + accountDto.getEmail(), accountDto);
    }
    @Override
    public RegisterStatus confirmAccount(String email) {
        AccountDto accountDto = (AccountDto) session.getAttribute("PENDING_ACCOUNT_" + email);
        if (accountDto == null) {
            return RegisterStatus.FAILED;
        }
        try {
            accountRepository.insertAccountAndUser(
                    accountDto.getUsername(),
                    passwordEncoder.encode(accountDto.getPassword()),
                    accountDto.getEmail(),
                    accountDto.getSub(),
                    accountDto.getIsEnabled(),
                    accountDto.getFullName());
            session.removeAttribute("PENDING_ACCOUNT_" + email);
            return RegisterStatus.SUCCEED;
        } catch (Exception e) {
            return RegisterStatus.FAILED;
        }
    }
    @Override
    public void sendVerificationEmail(String to, String link) throws MessagingException {
       MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setTo(to);
    helper.setSubject("Xác Nhận Tài Khoản");
    helper.setText("<p>Vui lòng click vào link xác nhận:</p>" +
            "<a href=\"" + link + "\">Verify</a>", true);
    mailSender.send(message);
    }
}