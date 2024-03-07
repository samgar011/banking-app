package com.minibanking.service.impl;

import com.minibanking.dto.request.UserRegistrationRequestDto;
import com.minibanking.model.entity.User;
import com.minibanking.repository.UserRepository;
import com.minibanking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<String> createUser(UserRegistrationRequestDto userRegistrationRequestDto) {

        User newUser = new User();
        newUser.setUserName(userRegistrationRequestDto.getUserName());
        newUser.setPassword(passwordEncoder.encode(userRegistrationRequestDto.getPassword()));
        newUser.setEmail(userRegistrationRequestDto.getEmail());
        userRepository.save(newUser);


        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
