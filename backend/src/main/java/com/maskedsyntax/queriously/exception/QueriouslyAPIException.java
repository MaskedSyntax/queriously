package com.maskedsyntax.queriously.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QueriouslyAPIException extends RuntimeException {
    private HttpStatus status;
    private String message;
}
