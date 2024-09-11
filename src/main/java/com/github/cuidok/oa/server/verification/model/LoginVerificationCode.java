package com.github.cuidok.oa.server.verification.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginVerificationCode {

    private String code;

    private LocalDateTime expireTime;
}
