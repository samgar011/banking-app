package com.minibanking.service;

import com.minibanking.dto.request.UserRegistrationRequestDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<String> createUser(UserRegistrationRequestDto userRegistrationRequestDto);
}
