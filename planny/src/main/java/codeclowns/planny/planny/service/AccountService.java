package codeclowns.planny.planny.service;

import codeclowns.planny.planny.data.dto.AccountDto;
import codeclowns.planny.planny.data.entity.Account;
import codeclowns.planny.planny.mapper.AccountMapper;
import codeclowns.planny.planny.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
//    @Autowired
//    private AccountMapper accountMapper;

//    public Account createAccount(AccountDto accountDto) {
//        Account account = accountMapper.toAccount(accountDto);
//        return accountRepository.save(account);
//    }


    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
