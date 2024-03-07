package com.minibanking.service.impl;

import com.minibanking.configuration.JwtTokenProvider;
import com.minibanking.configuration.security.BankingUserDetails;
import com.minibanking.dto.request.UserLoginRequest;
import com.minibanking.dto.response.UserLoginResponse;
import com.minibanking.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {


    private final AuthenticationManager authenticationManager;


    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UserLoginResponse login(UserLoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        BankingUserDetails userDetails = (BankingUserDetails) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(userDetails);

        return new UserLoginResponse(token);
    }

}
