package com.maskedsyntax.queriously.security;

import com.maskedsyntax.queriously.entity.User;
import com.maskedsyntax.queriously.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Custom implementation of {@link UserDetailsService} that loads user-specific
 * data.
 * <p>
 * This service retrieves a user from the database using a username or email,
 * converts the user's roles into Spring Security authorities, and returns a
 * fully populated {@link UserDetails} object.
 * </p>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * Constructs a new CustomUserDetailsService with the given UserRepository.
     *
     * @param userRepository the repository used to retrieve user data.
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads the user by username or email.
     *
     * @param usernameOrEmail the username or email used to search for the user.
     * @return a {@link UserDetails} object that contains user information and
     *         authorities.
     * @throws UsernameNotFoundException if no user is found with the given username
     *                                   or email.
     */
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        // Retrieve the user by username or email.
        User user = userRepository
                .findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with username or email: " + usernameOrEmail));

        // Map the user's roles to a set of GrantedAuthority objects.
        final Set<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toSet());

        // Create and return a UserDetails object using the user's information.
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(), 
            user.getPassword_hash(),
            grantedAuthorities);
    }
}
