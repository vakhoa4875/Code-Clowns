package codeclowns.planny.planny.security.controller;

import codeclowns.planny.planny.data.mgt.ResponseObject;
import codeclowns.planny.planny.security.data.CustomUserDetails;
import codeclowns.planny.planny.security.data.dto.LoginRequestDTO;
import codeclowns.planny.planny.security.data.dto.LoginResponseDTO;
import codeclowns.planny.planny.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
        System.out.println("/secured");
        var responseObject = new ResponseObject<>();
        responseObject.setMessage("If you see this, then you are logged in as user " + customUserDetails.getUsername() + "-password:" + customUserDetails.getPassword());
        return responseObject;
    }

    @PostMapping("/auth/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return authService.authenticate(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
    }

}
