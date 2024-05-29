package codeclowns.planny.planny.service;

import codeclowns.planny.planny.constant.LoginStatus;
import codeclowns.planny.planny.data.dto.AccountDto;

public interface AccountService {
    LoginStatus login(AccountDto accountDto);
}