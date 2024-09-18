package com.github.cuidok.oa.server.user.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Integer id;

    private String username;

    private String nickName;

    private String password;

    private String email;
}
