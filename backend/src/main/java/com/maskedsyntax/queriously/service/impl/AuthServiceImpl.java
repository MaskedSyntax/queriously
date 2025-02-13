package com.maskedsyntax.queriously.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDTO registerDTO) {

        // check if username is already in the database
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new QueriouslyAPIException(HttpStatus.BAD_REQUEST, "Username already Exists");
        }

        // check if email is already in the database
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new QueriouslyAPIException(HttpStatus.BAD_REQUEST, "Email already Exists");
        }

        User user = new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword_hash(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByRoleName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return "User Generated Successfully!";

    }

    @Override
    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
    }

}
