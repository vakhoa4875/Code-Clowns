package codeclowns.planny.planny.service;

import codeclowns.planny.planny.data.dto.AccountDto;

import java.util.List;

public interface AccountService {
    Object register(AccountDto accountDto);
    List<AccountDto> getAccountByUsername();
}