package com.github.cuidok.oa.server.verification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginVerificationKeyGeneratorTest {

    @Autowired
    private LoginVerificationKeyGenerator loginVerificationKeyGenerator;

    @Test
    void generateKey() {

        String key = loginVerificationKeyGenerator.generateKey();
        assertNotNull(key);
        System.out.println(key);
    }
}