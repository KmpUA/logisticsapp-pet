package com.yukon.logistics.configuration.security.jwt;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * JWT configuration for application that add {@link JwtAuthenticationFilter} for security chain.
 * It needs because in other approach filter don't skip public urls
 */
@RequiredArgsConstructor
public class JwtConfigurer
        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    
    @Override
    public void configure(@NonNull final HttpSecurity httpSecurity) {
        final var jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtService, userDetailsService);
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}