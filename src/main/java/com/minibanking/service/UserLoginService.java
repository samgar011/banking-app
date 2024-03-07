package com.minibanking.service;

import com.minibanking.dto.request.UserLoginRequest;
import com.minibanking.dto.response.UserLoginResponse;

public interface UserLoginService {
    UserLoginResponse login(UserLoginRequest loginRequest);
}
