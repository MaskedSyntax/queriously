package com.maskedsyntax.queriously.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception thrown when a user tries to perform an unauthorized action.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizedException extends RuntimeException{
    
    /**
     * Constructs a new UnauthorizedException with a message.
     *
     * @param message the error message
     */
    public UnauthorizedException(String message) {
        super(message);
    }
}
