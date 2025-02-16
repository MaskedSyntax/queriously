package com.maskedsyntax.queriously.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for user registration.
 * <p>
 * Contains the necessary details for registering a new user.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDTO {
    
    /**
     * The full name of the user.
     */
    private String name;
    
    /**
     * The unique username for the user.
     */
    private String username;
    
    /**
     * The email address of the user.
     */
    private String email;
    
    /**
     * The password chosen by the user.
     */
    private String password;
}
