package com.maskedsyntax.queriously.security;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filter that intercepts incoming HTTP requests to extract and validate JWT
 * tokens.
 * If the token is valid, the user authentication is set in the SecurityContext.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private static final String TOKEN_PREFIX = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    /**
     * Constructs a new JwtAuthenticationFilter.
     *
     * @param jwtTokenProvider   the provider for JWT token operations
     * @param userDetailsService the service to load user-specific data
     */
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Extracts the JWT token from the request header, validates it, and sets the
     * user authentication if valid.
     *
     * @param request     the HttpServletRequest
     * @param response    the HttpServletResponse
     * @param filterChain the FilterChain
     * @throws ServletException in case of servlet errors
     * @throws IOException      in case of I/O errors
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        // Extract JWT token from the Authorization header.
        Optional<String> tokenOpt = getTokenFromRequest(request);

        // Validate Token
        if (tokenOpt.isPresent()) {
            String token = tokenOpt.get();
            if (jwtTokenProvider.validateToken(token)) {
                try {
                    // Retrieve the username from the token
                    String username = jwtTokenProvider.getUsernameFromToken(token);

                    // Load user details using the username
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    // Create an authentication token with the user's details and authorities.
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Set the authentication in the SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } catch (Exception ex) {
                    logger.error("Could not set user authentication in security context", ex);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Retrieves the JWT token from the request's Authorization header.
     *
     * @param request the HttpServletRequest
     * @return an Optional containing the token if present and properly formatted, otherwise an empty Optional
     */
    private Optional<String> getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            return Optional.of(bearerToken.substring(TOKEN_PREFIX.length()));
        }

        return Optional.empty();
    }

}