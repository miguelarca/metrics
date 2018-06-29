package com.example.metrics.web.controllers;

import com.example.metrics.commons.exceptions.DomainException;
import com.example.metrics.commons.exceptions.ObjectNotFound;
import com.example.metrics.web.responses.ErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class MetricsControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = {ObjectNotFound.class})
    public ResponseEntity<ErrorResponse> handleNotFound(ObjectNotFound ex, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse("Not Found", HttpStatus.NOT_FOUND.value());
        errorResponse.setDescription(ex.getMessage());

        this.logger.error(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {DomainException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(DomainException ex, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse("Bad Request", HttpStatus.BAD_REQUEST.value());
        errorResponse.setDescription(ex.getMessage());

        this.logger.error(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidFormatException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(InvalidFormatException ex, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse("Bad Request", HttpStatus.BAD_REQUEST.value());

        String description = String.format("Deserialization error. value: %s, is not in the right format", ex.getValue().toString());

        errorResponse.setDescription(description);

        this.logger.error(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
