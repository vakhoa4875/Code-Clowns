package codeclowns.planny.planny.security.service;

import codeclowns.planny.planny.security.CustomUserDetails;
import codeclowns.planny.planny.security.jwt.JwtTokenProvider;
import codeclowns.planny.planny.security.jwt.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;;
    private final AuthenticationManager authenticationManager;;

    public LoginResponse authenticate(String username, String password) {
        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var userDetails = (CustomUserDetails) authentication.getPrincipal();
        var token = jwtTokenProvider.generateToken(userDetails);
        return LoginResponse.builder()
                .token(token)
                .build();
    }
}
