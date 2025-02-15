package com.maskedsyntax.queriously.service;

import com.maskedsyntax.queriously.dto.JwtAuthResponseDTO;
import com.maskedsyntax.queriously.dto.LoginDTO;
import com.maskedsyntax.queriously.dto.RegisterDTO;

/**
 * Service interface for handling authentication-related operations.
 */
public interface AuthService {
    /**
     * Registers a new user based on the provided registration details.
     *
     * @param registerDTO the registration details
     * @return a success message upon registration
     */
    String register(RegisterDTO registerDTO);
    
    /**
     * Authenticates a user and returns a JWT authentication response.
     *
     * @param loginDTO the login credentials
     * @return a JwtAuthResponseDTO containing the JWT token and user role
     */
    JwtAuthResponseDTO login(LoginDTO loginDTO);
}
