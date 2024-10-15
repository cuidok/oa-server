package com.github.cuidok.oa.server.user;

import com.github.cuidok.oa.server.user.mapper.UserQueryMapper;
import com.github.cuidok.oa.server.user.model.User;
import com.github.cuidok.oa.server.user.model.UserDetail;
import com.github.cuidok.oa.server.user.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserQueryService {

    private final UserQueryMapper userQueryMapper;

    private final UserTokenContainer userTokenContainer;

    private final UserTokenParse userTokenParse;

    @Autowired
    public UserQueryService(UserQueryMapper userQueryMapper, UserTokenContainer userTokenContainer, UserTokenParse userTokenParse) {
        this.userQueryMapper = userQueryMapper;
        this.userTokenContainer = userTokenContainer;
        this.userTokenParse = userTokenParse;
    }

    public UserInfo queryUserInfo(String token) {

        // Accept the token
        if (token == null || token.isEmpty()) {
            log.info("The token is null or empty");
            throw new IllegalArgumentException("Illegal token");
        }

        // Check the token whether it is contained in the token container
        if (userTokenContainer.get(token).isEmpty()) {
            log.info("The token {} is not contained in the token container", token);
            throw new IllegalArgumentException("Token is not valid");
        }

        // Parse the token
        UserDetail userDetail = userTokenParse.parseUserToken(token);

        // Query the user information by user id
        User userFromDb = userQueryMapper.selectUserById(userDetail.getId());
        if (userFromDb == null) {
            throw new IllegalArgumentException("User not found for the given token");
        }

        // 5. Return the user information
        return UserInfo.valueOf(userFromDb);
    }
}

