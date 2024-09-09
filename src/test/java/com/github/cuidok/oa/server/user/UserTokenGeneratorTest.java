package com.github.cuidok.oa.server.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTokenGeneratorTest {

    @Autowired
    private UserTokenGenerator userTokenGenerator;

    @Test
    void generateToken() {
        String token = userTokenGenerator.generateToken(1);
        assertNotNull(token);
        assertFalse(token.isEmpty());
        System.out.println(token);
    }
}