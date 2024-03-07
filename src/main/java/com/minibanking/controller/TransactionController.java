package com.minibanking.controller;

import com.minibanking.dto.request.TransferMoneyRequestDto;
import com.minibanking.model.entity.Transaction;
import com.minibanking.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.hibernate.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

import static com.minibanking.util.ApiConstant.TRN_API;

@RestController
@RequiredArgsConstructor
@RequestMapping(TRN_API)
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestBody TransferMoneyRequestDto transferMoneyRequestDto) {
        try {
            transactionService.transferMoney(transferMoneyRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Money transfer initiated successfully");
        } catch (TransactionException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<Optional<Transaction>> viewTransactionHistory(@PathVariable("accountId") UUID accountId) {
        Optional<Transaction> transactions = transactionService.getTransactionHistory(accountId);
        return ResponseEntity.ok(transactions);
    }
}
