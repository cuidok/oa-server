package com.github.cuidok.oa.server.user;

import com.github.cuidok.oa.server.user.model.UserDetail;

import java.util.Optional;

public interface UserTokenContainer {

    void save(String token, UserDetail userDetail);

    void remove(String token);

    Optional<UserDetail> get(String token);

}
