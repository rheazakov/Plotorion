package com.plotorion.plotorion.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomAppException.class)
    public ResponseEntity<?> handleCustomAppException(CustomAppException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());
        body.put("timestamp", Instant.now());
        return ResponseEntity.status(getStatusFromAnnotation(ex)).body(body);
    }

    private int getStatusFromAnnotation(Throwable ex) {
        ResponseStatus status = ex.getClass().getAnnotation(ResponseStatus.class);
        return status != null ? status.value().value() : 500;
    }
}
