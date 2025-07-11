package com.plotorion.plotorion.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends CustomAppException  {
    public UserNotFoundException(String message) {
        super(message);
    }
}
