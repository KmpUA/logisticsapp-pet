package com.yukon.logistics.configuration.security;


import com.yukon.logistics.common.ApplicationConstants;
import com.yukon.logistics.configuration.security.jwt.JwtAuthEntryPoint;
import com.yukon.logistics.configuration.security.jwt.JwtAuthenticationFilter;
import com.yukon.logistics.configuration.security.jwt.JwtService;
import com.yukon.logistics.exceptions.handler.JwtAccessDeniedHandler;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration class for JWT based Spring Security application.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig {
    
    UserDetailsService userDetailsService;
    JwtAuthEntryPoint jwtAuthEntryPoint;
    JwtAccessDeniedHandler jwtAccessDeniedHandler;
    
    @Bean
    public SecurityFilterChain filterChain(@NonNull final HttpSecurity http,
                                           @Autowired JwtService jwtService) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(requests -> requests.requestMatchers(
                                ApplicationConstants.Security.PublicRoutes.LOGIN_PATH,
                                ApplicationConstants.Security.PublicRoutes.TOKEN_REFRESH)
                        .permitAll().anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and()
                .exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler).and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(new JwtAuthenticationFilter(jwtService, userDetailsService),
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        final var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(
            @NonNull final AuthenticationConfiguration authConfig) throws Exception {
        
        return authConfig.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
