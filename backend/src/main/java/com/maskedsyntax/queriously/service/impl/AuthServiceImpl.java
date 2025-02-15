package com.maskedsyntax.queriously.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maskedsyntax.queriously.dto.JwtAuthResponseDTO;
import com.maskedsyntax.queriously.dto.LoginDTO;
import com.maskedsyntax.queriously.dto.RegisterDTO;
import com.maskedsyntax.queriously.entity.Role;
import com.maskedsyntax.queriously.entity.User;
import com.maskedsyntax.queriously.exception.QueriouslyAPIException;
import com.maskedsyntax.queriously.repository.RoleRepository;
import com.maskedsyntax.queriously.repository.UserRepository;
import com.maskedsyntax.queriously.security.JwtTokenProvider;
import com.maskedsyntax.queriously.service.AuthService;

import lombok.AllArgsConstructor;

/**
 * Implementation of the {@link AuthService} interface that provides authentication operations.
 * <p>
 * This implementation handles user registration and login while ensuring that the registration data
 * is valid and that a new user is correctly constructed from the registration DTO.
 * </p>
 */
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Registers a new user by validating the registration data, building the user entity, and saving it.
     *
     * @param registerDTO the registration details
     * @return a success message upon successful registration
     * @throws QueriouslyAPIException if the username or email is already taken or if the default role is missing
     */
    @Override
    public String register(RegisterDTO registerDTO) {

        // Validate registration details (username and email uniqueness)
        validateRegistration(registerDTO);

        // Build the User entity from registration DTO and assign the default role
        User user = buildUserFromRegistration(registerDTO);

        // Save the new user to the repository
        userRepository.save(user);

        return "User Generated Successfully!";
    }

    /**
     * Authenticates a user based on provided credentials and returns a JWT token with user role information.
     *
     * @param loginDTO the login credentials
     * @return a {@link JwtAuthResponseDTO} containing the JWT token and the role of the authenticated user
     */
    @Override
    public JwtAuthResponseDTO login(LoginDTO loginDTO) {
        // Authentication the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(authentication);

        // Retrieve the user to extract role information
        Optional<User> userOpt = userRepository.findByUsernameOrEmail(loginDTO.getUsernameOrEmail(), loginDTO.getUsernameOrEmail());

        String role = userOpt.flatMap(user ->
                user.getRoles().stream().findFirst().map(Role::getRoleName))
                .orElse(null);

        // Build and return the authentication response
        JwtAuthResponseDTO jwtAuthResponse = new JwtAuthResponseDTO();
        jwtAuthResponse.setAccessToken(token);
        jwtAuthResponse.setRole(role);

        return jwtAuthResponse;
    }

    /**
     * Validates that the registration details are unique.
     *
     * @param registerDTO the registration details to validate
     * @throws QueriouslyAPIException if the username or email already exists
     */
    private void validateRegistration(RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new QueriouslyAPIException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new QueriouslyAPIException(HttpStatus.BAD_REQUEST, "Email already exists");
        }
    }

    /**
     * Constructs a {@link User} entity from the provided registration data.
     * <p>
     * This method also encodes the user's password and assigns the default role.
     * </p>
     *
     * @param registerDTO the registration details
     * @return the constructed User entity
     * @throws QueriouslyAPIException if the default user role ("ROLE_USER") is not found
     */
    private User buildUserFromRegistration(RegisterDTO registerDTO) {
        User user = new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword_hash(passwordEncoder.encode(registerDTO.getPassword()));

        // Retrieve and assign the default role
        Role userRole = roleRepository.findByRoleName("ROLE_USER");
        if (userRole == null) {
            throw new QueriouslyAPIException(HttpStatus.INTERNAL_SERVER_ERROR, "Default user role not found");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        return user;
    }

}
