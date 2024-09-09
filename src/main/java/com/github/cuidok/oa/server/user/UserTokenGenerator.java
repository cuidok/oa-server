package com.github.cuidok.oa.server.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserTokenGenerator {

    @Value("${oa.server.jwt.secret}")
    private String SECRET_KEY;

    public String generateToken(Integer userId) {

        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        return JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date())
                .withExpiresAt(getExpirationDate())
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    private Date getExpirationDate() {
        // 7 days
        return new Date(System.currentTimeMillis() + 86400000 * 7);
    }
}
