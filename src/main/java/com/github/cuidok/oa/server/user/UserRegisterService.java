package com.github.cuidok.oa.server.user;

import com.github.cuidok.oa.server.user.model.User;
import com.github.cuidok.oa.server.user.model.UserRegisterParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserRegisterService {

    private final UserQueryMapper userQueryMapper;

    private final UserInsertMapper userInsertMapper;

    public UserRegisterService(UserQueryMapper userQueryMapper, UserInsertMapper userInsertMapper) {
        this.userQueryMapper = userQueryMapper;
        this.userInsertMapper = userInsertMapper;
    }

    public void register(UserRegisterParam param) {

        // Accept the register parameters
        String username = param.getUsername();
        String email = param.getEmail();
        String password = param.getPassword();

        // Check input parameters
        if (username == null || username.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        // Check the email whether meets the requirements
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Email does not meet the requirements");
        }

        // Check the password whether meets the requirements
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Password does not meet the requirements");
        }

        // Check the username whether exists
        User userFromDb = userQueryMapper.selectUserByUsername(username);
        if (userFromDb != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Check the email whether exists
        userFromDb = userQueryMapper.selectUserByEmail(email);
        if (userFromDb != null) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Use spring-security-crypto to encrypt the password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(password);

        // Save the user information into the user table
        User user = new User();
        user.setUsername(username);
        user.setNickName(param.getNickName());
        user.setPassword(encryptedPassword);
        user.setEmail(email);
        userInsertMapper.insert(user);
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                Pattern.compile("[a-zA-Z]").matcher(password).find() &&
                Pattern.compile("[0-9]").matcher(password).find();
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }
}
