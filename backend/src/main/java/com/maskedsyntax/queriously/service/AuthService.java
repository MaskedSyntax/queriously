package com.maskedsyntax.queriously.service;

import com.maskedsyntax.queriously.dto.RegisterDTO;

public interface AuthService {
    String register(RegisterDTO registerDTO);
}
