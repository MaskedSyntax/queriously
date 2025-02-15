package com.maskedsyntax.queriously.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A Data Transfer Object representing error details.
 * <p>
 * Contains information about the time of the error, an error message, and additional details.
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetails {
    /**
     * The timestamp when the error occurred.
     */
    private LocalDateTime timeStamp;
    
    /**
     * The error message.
     */
    private String message;
    
    /**
     * Additional details about the error.
     */
    private String details;
}
