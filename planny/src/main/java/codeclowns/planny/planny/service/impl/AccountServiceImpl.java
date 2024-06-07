package codeclowns.planny.planny.service.impl;

import codeclowns.planny.planny.constant.RegisterStatus;
import codeclowns.planny.planny.data.dto.AccountDto;
import codeclowns.planny.planny.data.entity.AccountE;
import codeclowns.planny.planny.repository.AccountRepository;
import codeclowns.planny.planny.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

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
}