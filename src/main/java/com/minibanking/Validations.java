package com.minibanking;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validations {
    public static final String USER_NOT_FOUND = "error.userNotFound";
    public static final String USER_EXIST = "error.userExist";
    public static final String ACC_NOT_FOUND = "error.accountNotFound";

}
