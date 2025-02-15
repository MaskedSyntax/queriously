package com.maskedsyntax.queriously.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handler to catch and handle exceptions thrown by
 * controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles QueriouslyAPIException exceptions.
     *
     * @param ex      the thrown QueriouslyAPIException
     * @param request the current web request
     * @return a ResponseEntity containing the error details and the HTTP status from the exception
     */
    @ExceptionHandler(QueriouslyAPIException.class)
    public ResponseEntity<ErrorDetails> handleQueriouslyAPIException(
            QueriouslyAPIException ex, WebRequest request) {

        ErrorDetails errorDetails = ErrorDetails.builder()
            .timeStamp(LocalDateTime.now())
            .message(ex.getMessage())
            .details(request.getDescription(false))
            .build();

        return new ResponseEntity<>(errorDetails, ex.getStatus());
    }
}
