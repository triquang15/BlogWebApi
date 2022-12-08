package com.devteam.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.devteam.utils.ValidationErrors;

import java.util.*;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ValidationErrorsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrors> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> errors.put(error.getField(), error.getDefaultMessage()));
        ValidationErrors validationErrors = new ValidationErrors(
                new Date(),
                errors,
                request.getDescription(false)
        );
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
}
