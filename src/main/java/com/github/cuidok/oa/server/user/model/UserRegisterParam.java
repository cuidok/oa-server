package com.github.cuidok.oa.server.user.model;

import lombok.Data;

@Data
public class UserRegisterParam {

    private String username;

    private String nickName;

    private String password;

    private String email;
}
