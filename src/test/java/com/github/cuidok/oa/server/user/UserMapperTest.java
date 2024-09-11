package com.github.cuidok.oa.server.user;

import com.github.cuidok.oa.server.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserInsertMapper userInsertMapper;

    @Autowired
    private UserQueryMapper userQueryMapper;

    @Autowired
    private UserDeleteMapper userDeleteMapper;

    @Test
    void test() {
        User user = new User();
        user.setUsername("test_" + System.currentTimeMillis());
        user.setNickName("test_nickname");
        user.setPassword("test_password");
        user.setSalt("test_salt");
        user.setEmail("test_email");

        userInsertMapper.insert(user);

        User userFromDatabase = userQueryMapper.selectUserByUsername(user.getUsername());
        assertNotNull(userFromDatabase);
        assertEquals(user.getUsername(), userFromDatabase.getUsername());
        assertEquals(user.getNickName(), userFromDatabase.getNickName());
        assertEquals(user.getPassword(), userFromDatabase.getPassword());
        assertEquals(user.getSalt(), userFromDatabase.getSalt());
        assertEquals(user.getEmail(), userFromDatabase.getEmail());

        userDeleteMapper.deleteUserById(userFromDatabase.getId());

        userFromDatabase = userQueryMapper.selectUserByUsername(user.getUsername());
        assertNull(userFromDatabase);
    }


}