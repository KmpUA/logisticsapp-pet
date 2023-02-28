package com.yukon.logistics.configuration.security.authentication;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.yukon.logistics.common.ApplicationConstants;
import com.yukon.logistics.configuration.security.jwt.JwtService;
import com.yukon.logistics.configuration.security.jwt.JwtUser;
import com.yukon.logistics.model.mapper.UserMapper;
import com.yukon.logistics.persistence.entity.Role;
import com.yukon.logistics.service.CookieService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationServiceImpl implements AuthenticationService {
    
    @Value("${spring.security.jwt.access-cookie-name}")
    String accessTokenCookieName;
    
    @Value("${spring.security.jwt.refresh-cookie-name}")
    String refreshTokenCookieName;
    
    @Value("${spring.security.jwt.access-cookie-lifetime}")
    long accessTokenCookieLifetime;
    
    @Value("${spring.security.jwt.refresh-cookie-lifetime}")
    long refreshTokenCookieLifetime;
    final UserDetailsService userDetailsService;
    final AuthenticationManager authenticationManager;
    final CookieService cookieService;
    final JwtService jwtService;
    final UserMapper userMapper;
    
    @Override
    public AuthenticationResponse authenticate(
            @Valid @NonNull final AuthenticationRequest authenticationRequest) {
        
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        
        final var user = (JwtUser) userDetailsService.loadUserByUsername(
                authenticationRequest.getEmail());
        
        final var cookieAccessToken = createAccessTokenCookie(
                user.getEmail(), user.getRole());
        
        final var cookieRefreshToken = createRefreshTokenCookie(user.getEmail());
        
        return AuthenticationResponse.builder()
                .cookieAccessToken(cookieAccessToken)
                .cookieRefreshToken(cookieRefreshToken)
                .authenticatedUser(userMapper.toAuthenticatedUser(user)).build();
    }
    
    @Override
    public AuthenticationResponse tokenRefresh(@NonNull final String refreshToken) {
        
        final var user = (JwtUser) userDetailsService.loadUserByUsername(
                jwtService.extractSubject(refreshToken));
        
        if (!jwtService.validateRefreshToken(refreshToken, user.getEmail())) {
            throw new JWTVerificationException("RefreshToken is invalid or expired");
        }
        
        return AuthenticationResponse.builder()
                .authenticatedUser(userMapper.toAuthenticatedUser(user))
                .cookieAccessToken(createAccessTokenCookie(user.getEmail(), user.getRole()))
                .cookieRefreshToken(createRefreshTokenCookie(user.getEmail()))
                .build();
    }
    
    @Override
    public AuthenticationResponse logout() {
        SecurityContextHolder.clearContext();
        
        return AuthenticationResponse.builder()
                .cookieRefreshToken(cookieService.deleteCookie(refreshTokenCookieName,
                        ApplicationConstants.Web.CookiePaths.REFRESH_TOKEN))
                .cookieAccessToken(cookieService.deleteCookie(accessTokenCookieName,
                        ApplicationConstants.Web.CookiePaths.ACCESS_TOKEN))
                .build();
    }
    
    /**
     * Create a cookie response with access token.
     *
     * @param email the user email to include in the token
     * @param role  user authority to include in the token
     * @return cookie response with access token
     */
    private ResponseCookie createAccessTokenCookie(@NonNull final String email,
                                                   @NonNull final Role role) {
        
        return cookieService.createCookie(accessTokenCookieName,
                jwtService.createAccessToken(email, role),
                ApplicationConstants.Web.CookiePaths.ACCESS_TOKEN, accessTokenCookieLifetime);
    }
    
    /**
     * Create a cookie response with refresh token.
     *
     * @param email the user email to include in the token
     * @return cookie response with refresh token
     */
    private ResponseCookie createRefreshTokenCookie(@NonNull final String email) {
        
        return cookieService.createCookie(refreshTokenCookieName,
                jwtService.createRefreshToken(email),
                ApplicationConstants.Web.CookiePaths.REFRESH_TOKEN, refreshTokenCookieLifetime);
    }
}
