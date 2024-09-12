package com.github.cuidok.oa.server.verification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginVerificationCodeGeneratorTest {

    @Autowired
    private LoginVerificationCodeGenerator loginVerificationCodeGenerator;

    @Test
    void generateCode() {
        String code = loginVerificationCodeGenerator.generateCode();
        assertNotNull(code);
        assertEquals(4, code.length());
        System.out.println(code);
    }
}