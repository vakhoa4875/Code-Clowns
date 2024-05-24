package codeclowns.planny.planny.api;

import codeclowns.planny.planny.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountApi {
    private final AccountService accountService;

}
