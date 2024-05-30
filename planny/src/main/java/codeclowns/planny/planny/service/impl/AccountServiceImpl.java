package codeclowns.planny.planny.service.impl;

import codeclowns.planny.planny.data.dto.AccountDto;
import codeclowns.planny.planny.data.entity.AccountE;
import codeclowns.planny.planny.data.mgt.ResponseObject;
import codeclowns.planny.planny.repository.AccountRepository;
import codeclowns.planny.planny.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseObject<?> register(AccountDto accountDto) {
        if (accountRepository.existsByUsername(accountDto.getUsername())) {
            log.info("Username {} already exists", accountDto.getUsername());
            return ResponseObject.failResponse("Username already exists");
        }

        if (accountRepository.existsByEmail(accountDto.getEmail())) {
            log.info("Email {} already exists", accountDto.getEmail());
            return ResponseObject.failResponse("Email already exists");
        }

        AccountE account = AccountE.builder()
                .username(accountDto.getUsername())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .email(accountDto.getEmail())
                .sub(accountDto.getSub())
                .isEnabled(accountDto.getIsEnabled())
                .build();

        accountRepository.save(account);
        return new ResponseObject<>(account);
    }

    @Override
    public List<AccountDto> getAccountByUsername() {
        return null;
    }

}