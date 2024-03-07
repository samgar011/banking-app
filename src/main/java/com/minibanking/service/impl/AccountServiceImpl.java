package com.minibanking.service.impl;

import com.minibanking.Validations;
import com.minibanking.dto.request.CreateAccountRequestDto;
import com.minibanking.dto.request.SearchAccountRequestDto;
import com.minibanking.dto.request.UpdateAccountRequestDto;
import com.minibanking.model.entity.Account;
import com.minibanking.model.entity.User;
import com.minibanking.repository.AccountRepository;
import com.minibanking.repository.UserRepository;
import com.minibanking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.minibanking.Validations.ACC_NOT_FOUND;
import static com.minibanking.Validations.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    @Override
    public Account createAccount(CreateAccountRequestDto accountCreationRequest) {
        Optional<User> user = userRepository.findById(accountCreationRequest.getUserId());
        if (user.isPresent()) {
            Account newAccount = new Account();
            newAccount.setUser(user.get());
            newAccount.setNumber(accountCreationRequest.getNumber());
            newAccount.setName(accountCreationRequest.getName());
            newAccount.setBalance(accountCreationRequest.getBalance());
            newAccount.setCreatedAt(LocalDateTime.now());
            newAccount.setUpdatedAt(LocalDateTime.now());
            return accountRepository.save(newAccount);
        } else {
            throw new ServiceException(USER_NOT_FOUND + accountCreationRequest.getUserId());
        }
    }

    @Override
    public Account updateAccount(UUID id, UpdateAccountRequestDto updateAccountRequestDto) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setName(updateAccountRequestDto.getName());
            account.setBalance(updateAccountRequestDto.getBalance());
            account.setUpdatedAt(LocalDateTime.now());
            return accountRepository.save(account);
        } else {
            throw new ServiceException(ACC_NOT_FOUND);
        }
    }

    @Override
    public Account searchAccount(SearchAccountRequestDto searchAccountRequestDto) {
        return null;
    }

    @Override
    public void deleteAccount(UUID id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Optional<Account> getAccountById(UUID id) {
        return accountRepository.findById(id);
    }
}
