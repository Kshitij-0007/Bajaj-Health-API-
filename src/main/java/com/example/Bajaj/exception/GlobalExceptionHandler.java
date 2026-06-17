package com.example.Bajaj.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError err : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(err.getField(), err.getDefaultMessage());
        }
        log.warn("Validation error: {}", fieldErrors);
        return ResponseEntity.badRequest().body(buildError(HttpStatus.BAD_REQUEST, "Validation failed", fieldErrors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleMalformedJson(HttpMessageNotReadableException ex) {
        log.warn("Malformed JSON: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(buildError(HttpStatus.BAD_REQUEST, "Malformed JSON request body", null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        log.error("Unhandled exception", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildError(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", null));
    }

    private Map<String, Object> buildError(HttpStatus status, String message, Object details) {
        Map<String, Object> body = new HashMap<>();
        body.put("is_success", false);
        body.put("status", status.value());
        body.put("error", message);
        body.put("timestamp", Instant.now().toString());
        if (details != null) {
            body.put("details", details);
        }
        return body;
    }
}
