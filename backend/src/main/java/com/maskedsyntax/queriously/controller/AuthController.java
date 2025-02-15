package com.maskedsyntax.queriously.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maskedsyntax.queriously.dto.JwtAuthResponseDTO;
import com.maskedsyntax.queriously.dto.LoginDTO;
import com.maskedsyntax.queriously.dto.RegisterDTO;
import com.maskedsyntax.queriously.service.AuthService;

/**
 * REST controller that handles authentication-related endpoints.
 * <p>
 * This controller provides endpoints for user registration and login.
 * </p>
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    /**
     * Constructs a new AuthController with the provided AuthService.
     *
     * @param authService the authentication service handling registration and login logic.
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Registers a new user.
     *
     * @param registerDTO the data transfer object containing registration details.
     * @return a {@link ResponseEntity} with a success message and HTTP status 201 (Created).
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        String response = authService.register(registerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Authenticates a user and issues a JWT token.
     *
     * @param loginDTO the data transfer object containing login credentials.
     * @return a {@link ResponseEntity} with the JWT authentication response and HTTP status 200 (OK).
     */
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        JwtAuthResponseDTO jwtAuthResponse = authService.login(loginDTO);
        
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
