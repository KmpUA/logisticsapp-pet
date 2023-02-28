package com.yukon.logistics.configuration.security.authentication;

import jakarta.validation.Valid;
import lombok.NonNull;

public interface AuthenticationService {
    
    /**
     * Authenticate user.
     * It checks username and password in the db, if it's valid
     * then create access and refresh tokens and put it into cookie,
     * then return cookies and user info
     *
     * @param authenticationRequest it contains user email and password
     * @return AuthenticationResponse
     * @see AuthenticationResponse
     */
    AuthenticationResponse authenticate(@Valid @NonNull final AuthenticationRequest authenticationRequest);
    
    /**
     * Refresh user authentication.
     * It checks if the refresh token in the cookies is valid
     * then create access and refresh tokens and put it into cookie,
     * then return cookies and user info
     *
     * @param refreshToken it contains refresh token
     * @return AuthenticationResponse
     * @see AuthenticationResponse
     */
    AuthenticationResponse tokenRefresh(@NonNull final String refreshToken);
    
    /**
     * Used to log out user.
     * It's clear security context authentication,
     * remove access and refresh tokens from cookies
     *
     * @return AuthenticationResponse that contains cookie that will remove in headers
     */
    AuthenticationResponse logout();
}
