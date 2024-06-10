package codeclowns.planny.planny.api;

import codeclowns.planny.planny.constant.BasicApiConstant;
import codeclowns.planny.planny.constant.RegisterStatus;
import codeclowns.planny.planny.data.dto.AccountDto;
import codeclowns.planny.planny.data.dto.ChangePasswordDto;
import codeclowns.planny.planny.data.mgt.ResponseObject;
import codeclowns.planny.planny.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-public/account")
@RequiredArgsConstructor
@Slf4j
public class AccountApi {
    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseObject<?> doPostRegister(@RequestBody AccountDto accountDto) {
        var response = new ResponseObject<>();
        try {
            var status = accountService.register(accountDto);
            if (status.equals(RegisterStatus.SUCCEED)) {
                response.setStatus(BasicApiConstant.SUCCEED.getStatus());
                response.setMessage(status.getStateDescription());
            } else {
                response.setStatus(BasicApiConstant.FAILED.getStatus());
                response.setMessage(status.getStateDescription());
            }
        } catch (Exception e) {
            response.setStatus(BasicApiConstant.ERROR.getStatus());
            response.setMessage(RegisterStatus.ERROR.getStateDescription());
        }
        return response;
    }

    @PostMapping("/change-password")
    public ResponseObject<?> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        var resultApi = new ResponseObject<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean changePasswordResult = accountService.changePassword(username, changePasswordDto);
        if (!changePasswordResult) {
            resultApi.setStatus(BasicApiConstant.FAILED.getStatus());
            resultApi.setMessage("Đổi mật khẩu không thành công");
            resultApi.setData(accountService.changePassword(username, changePasswordDto));
            return resultApi;
        }

        resultApi.setStatus(BasicApiConstant.SUCCEED.getStatus());
        resultApi.setMessage("Đổi mật khẩu thành công");
        resultApi.setData(accountService.changePassword(username, changePasswordDto));

        return resultApi;
    }
}
