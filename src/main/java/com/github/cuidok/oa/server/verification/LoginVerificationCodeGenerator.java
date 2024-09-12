package com.github.cuidok.oa.server.verification;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class LoginVerificationCodeGenerator {

    private static final String CHARACTERS = "abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789";

    private static final int CODE_LENGTH = 4;

    private final SecureRandom random = new SecureRandom();

    public String generateCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }
}
