package com.github.cuidok.oa.server.user.model;

import lombok.Data;

@Data
public class UserLoginParam {

    private String username;

    private String password;

    private String loginVerificationCode;

    private String loginVerificationKey;
}
