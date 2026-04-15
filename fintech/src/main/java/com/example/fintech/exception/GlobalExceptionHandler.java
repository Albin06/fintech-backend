package com.example.fintech.exception;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public Map<String, String> handle(CustomException ex) {
        return Map.of("error", ex.getMessage());
    }
}