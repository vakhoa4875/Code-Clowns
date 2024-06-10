package codeclowns.planny.planny.service.impl;

import codeclowns.planny.planny.constant.LoginStatus;
import codeclowns.planny.planny.constant.RegisterStatus;
import codeclowns.planny.planny.data.dto.AccountDto;
import codeclowns.planny.planny.data.dto.ChangePasswordDto;
import codeclowns.planny.planny.data.entity.AccountE;
import codeclowns.planny.planny.repository.AccountRepository;
import codeclowns.planny.planny.service.AccountService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession session;

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
        try {
            accountRepository.insertAccountAndUser(
                    accountDto.getUsername(),
                    passwordEncoder.encode(accountDto.getPassword()),
                    accountDto.getEmail(),
                    accountDto.getSub(),
                    accountDto.getIsEnabled(),
                    accountDto.getFullName());
            return RegisterStatus.SUCCEED;
        } catch (Exception e) {
            return RegisterStatus.FAILED;
        }
    }

    @Override
    public Optional<AccountE> findAccountByUsername(String username) {
        var account = accountRepository.findAccountByUsernameOrEmailAndIsEnabledTrue(username, username);
        return Optional.of(account);
    }

    @Override
    public boolean changePassword(String username, ChangePasswordDto changePasswordDto) {
        Optional<AccountE> accountOpt = accountRepository.findByUsername(username);
        if (accountOpt.isEmpty()) {
            return false;
        }
        AccountE account = accountOpt.get();
        if (!passwordEncoder.matches(changePasswordDto.getCurrentPassword(), account.getPassword())) {
            return false;
        }
        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmNewPassword())) {
            return false;
        }
        account.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        accountRepository.save(account);
        return true;
    }

}