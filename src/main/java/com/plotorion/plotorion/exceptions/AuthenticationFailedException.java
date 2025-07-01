package com.plotorion.plotorion.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationFailedException extends CustomAppException {
    public AuthenticationFailedException(String message) {
        super(message);
    }
}