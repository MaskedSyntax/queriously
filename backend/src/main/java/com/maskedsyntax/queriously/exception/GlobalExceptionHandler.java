package com.maskedsyntax.queriously.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(QueriouslyAPIException.class)
    public ResponseEntity<ErrorDetails> handleQueriouslyAPIException (
        QueriouslyAPIException queriouslyAPIException, WebRequest webRequest
    ) {

        ErrorDetails errorDetails = new ErrorDetails(
            LocalDateTime.now(), 
            queriouslyAPIException.getMessage(), 
            webRequest.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
