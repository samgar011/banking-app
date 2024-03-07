package com.minibanking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferMoneyRequestDto {
    private UUID fromAccountId;
    private UUID toAccountId;
    private BigDecimal amount;

}
