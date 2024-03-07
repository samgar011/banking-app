package com.minibanking.model.entity;

import com.minibanking.model.base.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.minibanking.util.EntityConstant.PREFIX;
import static com.minibanking.util.EntityConstant.USER;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = PREFIX + USER)
@Entity
public class User extends AbstractEntity {


    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role>roles;
}


