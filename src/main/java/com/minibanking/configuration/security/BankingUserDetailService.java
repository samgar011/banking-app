package com.minibanking.configuration.security;

import com.minibanking.model.entity.User;
import com.minibanking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.minibanking.Validations.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BankingUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(USER_NOT_FOUND);
        }
        return new BankingUserDetails(user);
    }
}

