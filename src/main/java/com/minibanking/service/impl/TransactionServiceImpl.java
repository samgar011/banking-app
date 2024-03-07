package com.minibanking.service.impl;

import com.minibanking.dto.request.TransferMoneyRequestDto;
import com.minibanking.model.entity.Account;
import com.minibanking.model.entity.Transaction;
import com.minibanking.model.enums.TransactionStatus;
import com.minibanking.repository.AccountRepository;
import com.minibanking.repository.TransactionRepository;
import com.minibanking.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    @Override
    public void transferMoney(TransferMoneyRequestDto transferMoneyRequestDto) {
        Optional<Account> fromAccountOptional = accountRepository.findById(transferMoneyRequestDto.getFromAccountId());
        Optional<Account> toAccountOptional = accountRepository.findById(transferMoneyRequestDto.getToAccountId());

        if (fromAccountOptional.isPresent() && toAccountOptional.isPresent()) {
            Account fromAccount = fromAccountOptional.get();
            Account toAccount = toAccountOptional.get();

            // Check if the from account has enough balance for the transfer
            BigDecimal transferAmount = transferMoneyRequestDto.getAmount();
            BigDecimal fromAccountBalance = fromAccount.getBalance();
            if (fromAccountBalance.compareTo(transferAmount) < 0) {
                throw new TransactionException("Insufficient balance for the transfer");
            }

            // Perform the transfer
            fromAccount.setBalance(fromAccountBalance.subtract(transferAmount));
            toAccount.setBalance(toAccount.getBalance().add(transferAmount));

            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);

            // Record the transaction
            Transaction transaction = new Transaction();
            transaction.setAccountFrom(transferMoneyRequestDto.getFromAccountId());
            transaction.setAccountTo(transferMoneyRequestDto.getToAccountId());
            transaction.setAmount(transferAmount);
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setStatus(TransactionStatus.SUCCESS);
            transactionRepository.save(transaction);
        } else {
            throw new TransactionException("One or both accounts involved in the transfer not found");
        }
    }

    @Override
    public Optional<Transaction> getTransactionHistory(UUID accountId) {
        return transactionRepository.findById(accountId);
    }
}
