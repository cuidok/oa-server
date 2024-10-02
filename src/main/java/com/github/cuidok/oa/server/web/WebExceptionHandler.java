package com.github.cuidok.oa.server.web;

import com.github.cuidok.oa.server.exception.NoPermissionException;
import com.github.cuidok.oa.server.user.exception.IllegalTokenException;
import com.github.cuidok.oa.server.web.model.BasicResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class WebExceptionHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(IllegalArgumentException.class)
    public BasicResponse<String> handleIllegalArgumentException(IllegalArgumentException e) {

        // Log the error information with error level
        log.info("Illegal argument: {}", e.getMessage());

        // Return the error information and error code to the user
        return BasicResponse.fail("400", e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(IllegalTokenException.class)
    public BasicResponse<String> handleIllegalTokenException(IllegalTokenException e) {

        // Log the error information with error level
        log.info("Illegal token: {}", e.getMessage());

        // Return the error information and error code to the user
        return BasicResponse.fail("400", e.getUserMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NoPermissionException.class)
    public BasicResponse<String> handleNoPermissionException(NoPermissionException e) {

        // Return the error information and error code to the user
        return BasicResponse.fail("403", e.getMessage());
    }
}
