package com.github.cuidok.oa.server.user;

import com.github.cuidok.oa.server.user.model.User;
import com.github.cuidok.oa.server.user.model.UserRegisterParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRegisterServiceTest {

    @Autowired
    private UserRegisterService userRegisterService;

    @Autowired
    private UserQueryMapper userQueryMapper;

    @Test
    void register() {

        UserRegisterParam param = new UserRegisterParam();
        param.setUsername("T_" + System.currentTimeMillis());
        param.setNickName("T_nickname");
        param.setPassword("T_password123");
        param.setEmail("T_" + System.currentTimeMillis() + "@test.com");

        userRegisterService.register(param);

        User userFromDatabase = userQueryMapper.selectUserByUsername(param.getUsername());

        assertNotNull(userFromDatabase);
        assertEquals(param.getUsername(), userFromDatabase.getUsername());
        assertEquals(param.getNickName(), userFromDatabase.getNickName());
        assertEquals(param.getEmail(), userFromDatabase.getEmail());
        assertTrue(new BCryptPasswordEncoder().matches(param.getPassword(), userFromDatabase.getPassword()));
    }
}