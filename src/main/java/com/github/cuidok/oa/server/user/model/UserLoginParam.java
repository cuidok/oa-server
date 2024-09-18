package com.github.cuidok.oa.server.user.model;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
public class UserLoginParam {

    private String username;

    private String password;

    private String loginVerificationCode;

    private String loginVerificationKey;
}
