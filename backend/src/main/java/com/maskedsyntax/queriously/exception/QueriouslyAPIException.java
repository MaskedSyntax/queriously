package com.maskedsyntax.queriously.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Custom runtime exception for the Queriously API.
 * <p>
 * Carries an HTTP status along with the error message for proper error response.
 * </p>
 */
@Getter
public class QueriouslyAPIException extends RuntimeException {
    private final HttpStatus status;

    /**
     * Constructs a new QueriouslyAPIException with the specified HTTP status and error message.
     *
     * @param status  the HTTP status to return
     * @param message the error message
     */
    public QueriouslyAPIException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
    private String message;
}
