package com.plotorion.plotorion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends CustomAppException  {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
