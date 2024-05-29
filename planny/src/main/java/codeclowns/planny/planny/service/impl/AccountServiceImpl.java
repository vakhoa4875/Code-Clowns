package codeclowns.planny.planny.service.impl;

import codeclowns.planny.planny.constant.LoginStatus;
import codeclowns.planny.planny.data.dto.AccountDto;
import codeclowns.planny.planny.repository.AccountRepository;
import codeclowns.planny.planny.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginStatus login(AccountDto accountDto) {
        var account = accountRepository.findByEmailOrUsername(accountDto.getEmail(), accountDto.getUsername());
        if (account == null) return LoginStatus.NOT_EXISTED;
        if (passwordEncoder.matches(accountDto.getPassword(), account.getPassword())) {
            LoginStatus.SUCCEED.setStateDescription("Chào mừng trở lại, " + account.getUsername() + "!");
            return LoginStatus.SUCCEED;
        }
        return LoginStatus.FAILED_PASSWORD;
    }
}