package com.github.cuidok.oa.server.user.exception;

import lombok.Getter;

@Getter
public class IllegalTokenException extends RuntimeException {

    private final String userMessage;

    public IllegalTokenException(String message, String userMessage) {
        super(message);
        this.userMessage = userMessage;
    }

    public IllegalTokenException(String message, String userMessage , Throwable cause) {
        super(message, cause);
        this.userMessage = userMessage;
    }

}