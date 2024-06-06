package codeclowns.planny.planny.controller;

import codeclowns.planny.planny.data.dto.AccountDto;
import codeclowns.planny.planny.data.mgt.ResponseObject;
import codeclowns.planny.planny.security.CustomUserDetails;
import codeclowns.planny.planny.security.jwt.LoginResponse;
import codeclowns.planny.planny.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/secured")
    public ResponseObject<?> secured(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        var responseObject = new ResponseObject<>();
        responseObject.setMessage("If you see this, then you are logged in as user " + customUserDetails.getUsername() + "-password:" + customUserDetails.getPassword());
        return responseObject;
    }

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody AccountDto accountDto) {
        var username = accountDto.getUsername() == null ? accountDto.getEmail() : accountDto.getUsername();
        var password = accountDto.getPassword();
        return authService.authenticate(username, password);
    }

}
