package com.github.cuidok.oa.server.encryptor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginVerificationKeyEncryptorTest {

    @Autowired
    private LoginVerificationKeyEncryptor encryptor;

    @Autowired
    private LoginVerificationKeyDecryptor decryptor;


    @Test
    void encrypt() {

        String key = "TEST001";

        String encrypted = encryptor.encrypt(key);
        String decrypted = decryptor.decrypt(encrypted);

        assertEquals(key, decrypted);
    }
}