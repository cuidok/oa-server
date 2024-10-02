package com.github.cuidok.oa.server.user;

import com.github.cuidok.oa.server.user.mapper.UserQueryMapper;
import com.github.cuidok.oa.server.user.model.User;
import com.github.cuidok.oa.server.user.model.UserDetail;
import com.github.cuidok.oa.server.user.model.UserLoginParam;
import com.github.cuidok.oa.server.verification.LoginVerificationCodeService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    private final UserQueryMapper userQueryMapper;

    private final UserTokenContainer userTokenContainer;

    private final UserTokenGenerator userTokenGenerator;

    private final LoginVerificationCodeService loginVerificationCodeService;

    public UserLoginService(UserQueryMapper userQueryMapper, UserTokenContainer userTokenContainer, UserTokenGenerator userTokenGenerator, LoginVerificationCodeService loginVerificationCodeService) {
        this.userQueryMapper = userQueryMapper;
        this.userTokenContainer = userTokenContainer;
        this.userTokenGenerator = userTokenGenerator;
        this.loginVerificationCodeService = loginVerificationCodeService;
    }

    public String login(UserLoginParam param) {

        // Accept the login parameters
        String username = param.getUsername();
        String password = param.getPassword();

        // Check input parameters
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        // Query the user information by username or email
        User user = userQueryMapper.selectUserByUsername(username);

        if (user == null) {
            throw new IllegalArgumentException("User does not exist");
        }

        // Query the login verification code
        if (!loginVerificationCodeService.verifyCode(param.getLoginVerificationKey(), param.getLoginVerificationCode())) {
            throw new IllegalArgumentException("Invalid verification code");
        }

        // Check the username and password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        // Generate the user token
        String token = userTokenGenerator.generateToken(user.getId());

        // Save the user token into the user token container
        UserDetail userDetail = new UserDetail(user.getId());
        userTokenContainer.save(token, userDetail);

        // Return the user token
        return token;
    }
}
