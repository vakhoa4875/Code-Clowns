package codeclowns.planny.planny.api;

import codeclowns.planny.planny.data.dto.AccountDto;
import codeclowns.planny.planny.data.entity.Account;
import codeclowns.planny.planny.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountApi {
    @Autowired
    private AccountService accountService;

//    @PostMapping
//    public Account createAccount (@RequestBody AccountDto accountDto) {
//        return accountService.createAccount(accountDto);
//    }

    @GetMapping
    public List<Account> getAccount() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{accountId}")
    public Account getAccountById(@PathVariable("accountId") Long accountId) {
        return accountService.getAccountById(accountId);
    }
}
