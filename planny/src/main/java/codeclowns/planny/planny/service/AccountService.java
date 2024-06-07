package codeclowns.planny.planny.service;

import codeclowns.planny.planny.constant.RegisterStatus;
import codeclowns.planny.planny.data.dto.AccountDto;
import codeclowns.planny.planny.data.entity.AccountE;

import java.util.Optional;

public interface AccountService {
    RegisterStatus register(AccountDto accountDto);

    Optional<AccountE> findAccountByUsername(String username);
}