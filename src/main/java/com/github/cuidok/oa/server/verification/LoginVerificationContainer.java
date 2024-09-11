package com.github.cuidok.oa.server.verification;

import com.github.cuidok.oa.server.verification.model.LoginVerificationCode;

import java.util.Optional;

public interface LoginVerificationContainer {

    LoginVerificationCode save(String key, String code);

    void remove(String key);

    Optional<LoginVerificationCode> get(String key);

}
