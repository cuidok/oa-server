package com.github.cuidok.oa.server.user.model;

import lombok.Data;

@Data
public class UserInfo {

    private Integer id;

    private String username;

    private String nickName;

    private String email;

    public static UserInfo valueOf(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setUsername(user.getUsername());
        userInfo.setNickName(user.getNickName());
        userInfo.setEmail(user.getEmail());
        return userInfo;
    }
}
