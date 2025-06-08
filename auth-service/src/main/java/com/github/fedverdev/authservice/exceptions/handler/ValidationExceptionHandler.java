package com.github.fedverdev.authservice.exceptions.handler;

import com.github.fedverdev.authservice.exceptions.UsernameAlreadyExistsException;
import com.github.fedverdev.authservice.exceptions.handler.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(new ErrorResponse(
                new Timestamp(System.currentTimeMillis()),
                400,
                errors,
                request.getRequestURI()
        ));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new HashMap<>();
        errors.put("request_body", "Required request body is empty");
        return ResponseEntity.badRequest().body(new ErrorResponse(
                new Timestamp(System.currentTimeMillis()),
                400,
                errors,
                request.getRequestURI()
        ));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            UsernameAlreadyExistsException ex,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new HashMap<>();
        errors.put("username", ex.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(
                new Timestamp(System.currentTimeMillis()),
                400,
                errors,
                request.getRequestURI()
        ));
    }
}

