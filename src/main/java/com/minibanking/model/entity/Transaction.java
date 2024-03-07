package com.minibanking.model.entity;

import com.minibanking.model.base.AbstractEntity;
import com.minibanking.model.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.minibanking.util.EntityConstant.PREFIX;
import static com.minibanking.util.EntityConstant.TRANSACTION;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = PREFIX + TRANSACTION)
public class Transaction extends AbstractEntity {


    private UUID accountFrom;

    private UUID accountTo;

    private BigDecimal amount;

    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;


}
