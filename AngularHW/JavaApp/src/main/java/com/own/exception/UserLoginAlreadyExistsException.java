package com.own.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserLoginAlreadyExistsException extends Exception {
    public UserLoginAlreadyExistsException(String message) {
        super(message);
    }
}
