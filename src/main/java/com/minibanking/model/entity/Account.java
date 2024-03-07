package com.minibanking.model.entity;

import com.minibanking.model.base.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.List;

import static com.minibanking.util.EntityConstant.ACCOUNT;
import static com.minibanking.util.EntityConstant.PREFIX;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = PREFIX + ACCOUNT)
public class Account extends AbstractEntity {

    @Column(name = "number")
    private String number;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
