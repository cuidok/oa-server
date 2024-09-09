package com.github.cuidok.oa.server.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.github.cuidok.oa.server.user.exception.IllegalTokenException;
import com.github.cuidok.oa.server.user.model.UserDetail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserTokenParse {

    @Value("${oa.server.jwt.secret}")
    private String SECRET_KEY;

    public UserDetail parseUserToken(String token) {

        if (token == null || token.isEmpty()) {
            throw new IllegalTokenException("This token is an illegal token, because it is null or empty.", "Illegal token");
        }

        try {

            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);

            UserDetail userDetail = new UserDetail();
            userDetail.setId(Integer.parseInt(jwt.getSubject()));
            return userDetail;

        } catch (JWTVerificationException exception) {
            throw new IllegalTokenException("This token is an illegal token, because an exception occurring during parse.", "Illegal token" ,exception);
        }
    }

}
