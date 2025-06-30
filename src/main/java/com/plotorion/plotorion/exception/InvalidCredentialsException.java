package com.plotorion.plotorion.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidCredentialsException extends CustomAppException{
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
