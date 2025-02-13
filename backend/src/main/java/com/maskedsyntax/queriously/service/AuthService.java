package com.maskedsyntax.queriously.service;

import com.maskedsyntax.queriously.dto.JwtAuthResponseDTO;
import com.maskedsyntax.queriously.dto.LoginDTO;
import com.maskedsyntax.queriously.dto.RegisterDTO;

public interface AuthService {
    String register(RegisterDTO registerDTO);
    JwtAuthResponseDTO login(LoginDTO loginDTO);
}
