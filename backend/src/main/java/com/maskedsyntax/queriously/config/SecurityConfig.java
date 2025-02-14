package com.maskedsyntax.queriously.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.maskedsyntax.queriously.security.JwtAuthenticationEntryPoint;
import com.maskedsyntax.queriously.security.JwtAuthenticationFilter;

/**
 * Security configuration class that defines the application's security filter
 * chain and authentication mechanisms.
 * <p>
 * This configuration disables CSRF, configures global authorization rules, sets
 * up HTTP Basic authentication, and integrates JWT authentication.
 * </p>
 * <p>
 * Although the {@code UserDetailsService} is not directly referenced in this
 * configuration, it is injected to ensure Spring Security can auto-configure authentication
 * based on your custom user details service.
 * </p>
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Constructs a new SecurityConfig.
     *
     * @param userDetailsService          the UserDetailsService used for loading
     *                                    user-specific data.
     * @param jwtAuthenticationEntryPoint the entry point handling unauthorized
     *                                    access attempts.
     * @param jwtAuthenticationFilter     the JWT filter for validating tokens
     *                                    before username/password authentication.
     */
    public SecurityConfig(UserDetailsService userDetailsService,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /**
     * Provides a BCrypt-based password encoder bean.
     *
     * @return a BCryptPasswordEncoder instance.
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the SecurityFilterChain.
     *
     * @param http the HttpSecurity instance to configure.
     * @return the configured SecurityFilterChain.
     * @throws Exception in case of configuration errors.
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> {

                    // authorizeRequests.requestMatchers(HttpMethod.POST, "/api/**")
                    // .hasRole("ADMIN");
                    // authorizeRequests.requestMatchers(HttpMethod.PUT, "/api/**")
                    // .hasRole("ADMIN");
                    // authorizeRequests.requestMatchers(HttpMethod.DELETE, "/api/**")
                    // .hasRole("ADMIN");
                    // authorizeRequests.requestMatchers(HttpMethod.GET, "/api/**")
                    // .hasAnyRole("ADMIN", "USER");

                    // Permit access to authentication endpoints and OPTIONS requests globally.
                    authorizeRequests.requestMatchers("/api/auth/**")
                            .permitAll();
                    authorizeRequests.requestMatchers(HttpMethod.OPTIONS, "/**")
                            .permitAll();
                    authorizeRequests
                            .anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint));

        // Add the JWT authentication filter before the default username/password filter.
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Exposes the AuthenticationManager bean.
     *
     * @param configuration the AuthenticationConfiguration.
     * @return the AuthenticationManager instance.
     * @throws Exception in case of configuration errors.
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
