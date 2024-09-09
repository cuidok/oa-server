package com.github.cuidok.oa.server.user;

import com.github.cuidok.oa.server.user.model.UserDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTokenParseTest {

    @Autowired
    private UserTokenGenerator userTokenGenerator;

    @Autowired
    private UserTokenParse userTokenParse;

    @Test
    void parseUserToken() {

        String token = userTokenGenerator.generateToken(1);

        UserDetail userDetail = userTokenParse.parseUserToken(token);

        assertEquals(1, userDetail.getId());
    }
}