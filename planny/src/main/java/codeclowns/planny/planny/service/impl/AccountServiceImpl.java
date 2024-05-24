package codeclowns.planny.planny.service.impl;

import codeclowns.planny.planny.repository.AccountRepository;
import codeclowns.planny.planny.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
}