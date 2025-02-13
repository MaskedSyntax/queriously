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

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        String response = authService.register(registerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        JwtAuthResponseDTO jwtAuthResponse = authService.login(loginDTO);
        
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
