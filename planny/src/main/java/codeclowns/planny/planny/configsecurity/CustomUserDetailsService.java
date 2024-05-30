package codeclowns.planny.planny.configsecurity;

import codeclowns.planny.planny.data.entity.AccountE;
import codeclowns.planny.planny.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountE accountE = accountRepository.findByUsername(username);
        if (accountE == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(accountE);
    }
}
