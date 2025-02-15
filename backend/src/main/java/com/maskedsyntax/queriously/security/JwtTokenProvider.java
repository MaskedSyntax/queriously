package com.maskedsyntax.queriously.security;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private Long jwtExpirationMs;

    private Key key;

    /**
     * Initializes the JWT token provider by decoding the secret and caching the
     * key.
     */
    @PostConstruct
    public void init() {
        // Decode the base64 encoded JWT secret and cache it so we don't decode it on
        // every method call
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    /**
     * Generates a JWT token based on the authenticated user's details.
     *
     * @param authentication the Authentication object containing the user details
     * @return a JWT token as a String
     */
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();

    }

    /**
     * Extracts the username from the provided JWT token.
     *
     * @param token the JWT token
     * @return the username embedded in the token
     */
    public String getUsernameFromToken(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Validates the provided JWT token.
     *
     * @param token the JWT token to validate
     * @return true if the token is valid; false otherwise
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parse(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            logger.error("Invalid JWT token", e);
            return false;
        }
    }

}