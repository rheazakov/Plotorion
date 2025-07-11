package com.plotorion.plotorion.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidTokenException extends CustomAppException {
  public InvalidTokenException(String message) {
    super(message);
  }
}