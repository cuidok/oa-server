package com.github.cuidok.oa.server.user.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserToken {

    private String token;

    private UserDetail userDetail;

    private LocalDateTime expireTime;

    public UserToken(String token, UserDetail userDetail, LocalDateTime expireTime) {
        this.token = token;
        this.userDetail = userDetail;
        this.expireTime = expireTime;
    }
}
