package com.github.cuidok.oa.server.user;

import com.github.cuidok.oa.server.user.model.UserDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CHMUserTokenContainerTest {

    @Autowired
    private CHMUserTokenContainer chmUserTokenContainer;


    @Test
    void save() {

        String token = "TEST001";
        UserDetail userDetail = new UserDetail();
        userDetail.setId(1);

        chmUserTokenContainer.save(token, userDetail);

        Optional<UserDetail> userDetailOptional = chmUserTokenContainer.get(token);
        assertTrue(userDetailOptional.isPresent());

        UserDetail userDetailCache = userDetailOptional.get();
        assertEquals(userDetail.getId(), userDetailCache.getId());
    }

    @Test
    void remove() {

        String token = "TEST002";
        UserDetail userDetail = new UserDetail();
        userDetail.setId(2);

        chmUserTokenContainer.save(token, userDetail);

        Optional<UserDetail> userDetailOptional = chmUserTokenContainer.get(token);
        assertTrue(userDetailOptional.isPresent());

        chmUserTokenContainer.remove(token);
        userDetailOptional = chmUserTokenContainer.get(token);
        assertTrue(userDetailOptional.isEmpty());
    }
}