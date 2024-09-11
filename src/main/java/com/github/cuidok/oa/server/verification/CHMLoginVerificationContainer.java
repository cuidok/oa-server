package com.github.cuidok.oa.server.verification;

import com.github.cuidok.oa.server.verification.model.LoginVerificationCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CHMLoginVerificationContainer implements LoginVerificationContainer {

    private final ConcurrentHashMap<String, LoginVerificationCode> verificationMap = new ConcurrentHashMap<>();

    @Override
    public LoginVerificationCode save(String key, String code) {
        if (key == null || code == null) {
            throw new IllegalArgumentException("Login verification key must not be null");
        }
        LoginVerificationCode verificationCode = new LoginVerificationCode();
        verificationCode.setCode(code);
        verificationCode.setExpireTime(LocalDateTime.now().plusMinutes(5));
        verificationMap.put(key, verificationCode);
        return verificationCode;
    }

    @Override
    public void remove(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Login verification key must not be null");
        }
        verificationMap.remove(key);
    }

    @Override
    public Optional<LoginVerificationCode> get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Login verification key must not be null");
        }
        LoginVerificationCode verificationCode = verificationMap.get(key);
        if (verificationCode == null || verificationCode.getExpireTime().isBefore(LocalDateTime.now())) {
            verificationMap.remove(key);
            return Optional.empty();
        }
        return Optional.of(verificationCode);
    }

}
