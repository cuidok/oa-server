package com.github.cuidok.oa.server.user.model;

import lombok.Data;

@Data
public class UserDetail {

    private Integer id;

    public UserDetail() {
    }

    public UserDetail(Integer id) {
        this.id = id;
    }
}
