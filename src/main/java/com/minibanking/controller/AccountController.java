package com.minibanking.controller;


import com.minibanking.dto.request.CreateAccountRequestDto;
import com.minibanking.dto.request.SearchAccountRequestDto;
import com.minibanking.dto.request.UpdateAccountRequestDto;
import com.minibanking.model.entity.Account;
import com.minibanking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.minibanking.util.ApiConstant.ACC_API;

@RestController
@RequiredArgsConstructor
@RequestMapping(ACC_API)
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountRequestDto
                                                             accountCreationRequest) {
        Account newAccount = accountService.createAccount(accountCreationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") UUID id, @RequestBody
    UpdateAccountRequestDto accountUpdateRequest) {
        Account updatedAccount = accountService.updateAccount(id, accountUpdateRequest);
        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Account>> searchAccounts(@RequestBody SearchAccountRequestDto
                                                                    searchAccountRequestDto) {
        Account accounts = accountService.searchAccount(searchAccountRequestDto);
        return ResponseEntity.ok(Collections.singletonList(accounts));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("id") UUID id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> viewAccountDetails(@PathVariable("id") UUID id) {
        Optional<Account> account = accountService.getAccountById(id);
        if (account.isPresent()) {
            return ResponseEntity.ok(account.get());
        }
        return ResponseEntity.notFound().build();
    }


}
