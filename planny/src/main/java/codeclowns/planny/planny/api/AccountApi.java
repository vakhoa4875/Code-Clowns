package codeclowns.planny.planny.api;

import codeclowns.planny.planny.constant.ApiMessage;
import codeclowns.planny.planny.data.dto.AccountDto;
import codeclowns.planny.planny.data.mgt.ResponseObject;
import codeclowns.planny.planny.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-public/account")
@RequiredArgsConstructor
@Slf4j
public class AccountApi {
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseObject<?> doPostRegisterAccount(@RequestBody AccountDto accountDto) {
        var resultApi = new ResponseObject<>();
        try {
            ResponseObject<?> result = (ResponseObject<?>) accountService.register(accountDto);
            if (result.isSuccess()) {
                resultApi.setSuccess(true);
                resultApi.setMessage(result.getMessage());
                resultApi.setData(result.getData());
            } else {
                resultApi.setSuccess(false);
                resultApi.setMessage(result.getMessage());
            }
        } catch (Exception e) {
            resultApi.setSuccess(false);
            resultApi.setMessage(ApiMessage.BasicMessageApi.FAIL.getBasicMessageApi());
            log.error("Fail When Call API /api-public/account/register: ", e);
        }
        return resultApi;
    }
}
