package com.minibanking.service;

import com.minibanking.dto.request.TransferMoneyRequestDto;
import com.minibanking.model.entity.Transaction;

import java.util.Optional;
import java.util.UUID;

public interface TransactionService {
    void transferMoney(TransferMoneyRequestDto transferMoneyRequestDto);

    Optional<Transaction> getTransactionHistory(UUID accountId);
}
