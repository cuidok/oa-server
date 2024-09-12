package com.github.cuidok.oa.server.verification;

import com.github.cuidok.oa.server.encryptor.LoginVerificationKeyDecryptor;
import com.github.cuidok.oa.server.verification.model.LoginVerificationCode;
import org.springframework.stereotype.Service;

@Service
public class LoginVerificationCodeService {

    private final LoginVerificationCodeGenerator codeGenerator;

    private final LoginVerificationContainer container;

    private final LoginVerificationImageGenerator imageGenerator;

    private final LoginVerificationKeyDecryptor decryptor;

    public LoginVerificationCodeService(LoginVerificationCodeGenerator codeGenerator, LoginVerificationContainer container, LoginVerificationImageGenerator imageGenerator, LoginVerificationKeyDecryptor loginVerificationKeyDecryptor) {
        this.codeGenerator = codeGenerator;
        this.container = container;
        this.imageGenerator = imageGenerator;
        this.decryptor = loginVerificationKeyDecryptor;
    }

    public byte[] generateCodeImage(String key) {

        if (key == null || key.isEmpty() || key.trim().isEmpty()) {
            throw new IllegalArgumentException("The key must be not null.");
        }

        String code = codeGenerator.generateCode();

        String keyOriginal = decryptor.decrypt(key);
        LoginVerificationCode loginVerificationCode = container.save(keyOriginal, code);

        return imageGenerator.generateImage(loginVerificationCode.getCode());
    }

    public boolean verifyCode(String key, String inputCode) {

        if (key == null || key.isEmpty() || key.trim().isEmpty()) {
            throw new IllegalArgumentException("The key must be not null.");
        }

        if (inputCode == null || inputCode.isEmpty() || inputCode.trim().isEmpty()) {
            throw new IllegalArgumentException("The input code must be not null.");
        }

        String keyOriginal = decryptor.decrypt(key);

        return container.get(keyOriginal).map(loginVerificationCode -> {
                    boolean isMatch = loginVerificationCode.getCode().equalsIgnoreCase(inputCode);
                    if (isMatch) {
                        container.remove(keyOriginal);
                    }
                    return isMatch;
                })
                .orElse(false);
    }
}
