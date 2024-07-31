package com.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity; enable it in production
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/login").permitAll() // Allow access to /auth/login without authentication
                        .anyRequest().authenticated() // All other endpoints require authentication
                )
                .httpBasic(withDefaults()); // Use HTTP Basic authentication for other endpoints

        return http.build();
    }
}
