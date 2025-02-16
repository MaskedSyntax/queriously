package com.maskedsyntax.queriously.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for JWT authentication responses.
 * <p>
 * Encapsulates the JWT access token, token type, and the role of the authenticated user.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtAuthResponseDTO {
    
    /**
     * The JWT access token.
     */
    private String accessToken;
    
    /**
     * The token type (e.g., "Bearer"). Defaults to "Bearer".
     */
    @Builder.Default
    private String tokenType = "Bearer";
    
    /**
     * The role associated with the authenticated user.
     */
    private String role;
}