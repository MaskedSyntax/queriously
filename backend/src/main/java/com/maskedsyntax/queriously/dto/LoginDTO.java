package com.maskedsyntax.queriously.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for user login.
 * <p>
 * Contains credentials needed for authenticating a user.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {
    
    /**
     * The username or email address used for login.
     */
    private String usernameOrEmail;
    
    /**
     * The password for login.
     */
    private String password;
}