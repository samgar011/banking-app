package com.minibanking.controller;

import com.minibanking.dto.request.UserLoginRequest;
import com.minibanking.dto.request.UserRegistrationRequestDto;
import com.minibanking.dto.response.UserLoginResponse;
import com.minibanking.service.UserLoginService;
import com.minibanking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.minibanking.util.ApiConstant.USER_API;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER_API)
public class UserController {

private final UserService userService;
private final UserLoginService userLoginService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserRegistrationRequestDto registrationRequestDto) {
        userService.createUser(registrationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/api/users/login")
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody UserLoginRequest loginRequest) {
        UserLoginResponse response = userLoginService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

}
