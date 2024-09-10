package com.github.cuidok.oa.server.user;

import com.github.cuidok.oa.server.user.exception.IllegalTokenException;
import com.github.cuidok.oa.server.user.model.UserDetail;
import com.github.cuidok.oa.server.user.model.UserToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CHMUserTokenContainer implements UserTokenContainer {

    private final ConcurrentHashMap<String, UserToken> tokenMap = new ConcurrentHashMap<>();

    @Override
    public void save(String token, UserDetail userDetail) {
        if (token == null || userDetail == null) {
            throw new IllegalTokenException("Token or UserDetail is null", "Illegal token");
        }
        UserToken userToken = new UserToken(token, userDetail, LocalDateTime.now().plusDays(7));
        tokenMap.put(token, userToken);
    }

    @Override
    public void remove(String token) {
        if (token == null) {
            throw new IllegalTokenException("Token is null", "Illegal token");
        }
        tokenMap.remove(token);
    }

    @Override
    public Optional<UserDetail> get(String token) {
        if (token == null) {
            throw new IllegalTokenException("Token is null", "Illegal token");
        }
        UserToken userToken = tokenMap.get(token);
        if (userToken == null || userToken.getExpireTime().isBefore(LocalDateTime.now())) {
            return Optional.empty();
        }
        return Optional.of(userToken.getUserDetail());
    }
}
