package com.github.cuidok.oa.server.verification;

import com.github.cuidok.oa.server.encryptor.LoginVerificationKeyEncryptor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class LoginVerificationKeyGenerator {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final LoginVerificationKeyEncryptor encryptor;

    public LoginVerificationKeyGenerator(LoginVerificationKeyEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    public String generateKey(){

        // Get the current time
        String currentTime = LocalDateTime.now().format(DATE_TIME_FORMATTER);

        // Generate a random number from 10000 to 99999
        SecureRandom random = new SecureRandom();
        int randomNumber = 10000 + random.nextInt(90000);

        // Combine the current time and the random number to generate the login verification key
        // Return the login verification key
        return currentTime + randomNumber;
    }
}
