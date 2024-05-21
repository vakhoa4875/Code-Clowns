package codeclowns.planny.planny.mapper;

import codeclowns.planny.planny.data.dto.AccountDto;
import codeclowns.planny.planny.data.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(AccountDto accountDto);
}
