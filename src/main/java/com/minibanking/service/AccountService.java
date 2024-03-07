package com.minibanking.service;

import com.minibanking.dto.request.CreateAccountRequestDto;
import com.minibanking.dto.request.SearchAccountRequestDto;
import com.minibanking.dto.request.UpdateAccountRequestDto;
import com.minibanking.model.entity.Account;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

public interface AccountService {
    Account createAccount(CreateAccountRequestDto accountCreationRequest);

    Account updateAccount(UUID id, UpdateAccountRequestDto updateAccountRequestDto);

    Account searchAccount(SearchAccountRequestDto searchAccountRequestDto);

    void deleteAccount(UUID id);

    Optional<Account> getAccountById(UUID id);
}
