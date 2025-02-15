package com.maskedsyntax.queriously.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Handles unauthorized access attempts by sending a 401 (Unauthorized) response.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Called when an authentication error occurs.
     *
     * @param request       the HttpServletRequest
     * @param response      the HttpServletResponse
     * @param authException the authentication exception that was thrown
     * @throws IOException      in case of I/O errors
     * @throws ServletException in case of servlet errors
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }

}
