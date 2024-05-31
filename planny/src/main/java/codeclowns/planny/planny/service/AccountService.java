package codeclowns.planny.planny.service;

import codeclowns.planny.planny.constant.LoginStatus;
import codeclowns.planny.planny.constant.RegisterStatus;
import codeclowns.planny.planny.data.dto.AccountDto;
import codeclowns.planny.planny.data.mgt.ResponseObject;

public interface AccountService {
    LoginStatus login(AccountDto accountDto);
    RegisterStatus register(AccountDto accountDto);
}