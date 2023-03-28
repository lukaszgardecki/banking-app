package com.example.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(request -> request
                .requestMatchers("/login", "/register", "/verify").permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers(
                        "/css/**",
                        "/images/**",
                        "/scripts/**").permitAll()
                .anyRequest().authenticated());
        return http.build();
    }
}
