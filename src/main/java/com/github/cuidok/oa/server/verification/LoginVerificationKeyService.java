package com.github.cuidok.oa.server.verification;

import com.github.cuidok.oa.server.encryptor.LoginVerificationKeyEncryptor;
import org.springframework.stereotype.Service;

@Service
public class LoginVerificationKeyService {

    private final LoginVerificationContainer loginVerificationContainer;

    private final LoginVerificationKeyGenerator loginVerificationKeyGenerator;

    private final LoginVerificationKeyEncryptor encryptor;

    public LoginVerificationKeyService(LoginVerificationContainer loginVerificationContainer, LoginVerificationKeyGenerator loginVerificationKeyGenerator, LoginVerificationKeyEncryptor encryptor) {
        this.loginVerificationContainer = loginVerificationContainer;
        this.loginVerificationKeyGenerator = loginVerificationKeyGenerator;
        this.encryptor = encryptor;
    }

    public String generateKey() {

        String key = loginVerificationKeyGenerator.generateKey();

        while (loginVerificationContainer.get(key).isPresent()) {
            key = loginVerificationKeyGenerator.generateKey();
        }

        return encryptor.encrypt(key);
    }
}
