package com.yukon.logistics.configuration.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yukon.logistics.common.ApplicationConstants;
import com.yukon.logistics.persistence.entity.Role;
import com.yukon.logistics.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtServiceImpl implements JwtService {
    
    @Value("${spring.security.jwt.access-validity}")
    Long accessTokenValidityInMs;
    
    @Value("${spring.security.jwt.refresh-validity}")
    Long refreshTokenValidityInMs;
    
    @Value("${spring.security.jwt.access-cookie-name}")
    String accessTokenCookieName;
    
    @Value("${spring.security.jwt.refresh-cookie-name}")
    String refreshTokenCookieName;
    
    final JwtSignProvider signAlgorithm;
    final CookieService cookieService;
    
    @Override
    public String getAccessTokenFromRequest(@NonNull HttpServletRequest request) {
        return cookieService.getCookieValueFromRequest(request, accessTokenCookieName);
    }
    
    @Override
    public String getRefreshTokenFromRequest(@NonNull HttpServletRequest request) {
        return cookieService.getCookieValueFromRequest(request, refreshTokenCookieName);
    }
    
    @Override
    public String extractSubject(@NonNull final String token) {
        return extractDecodedJWT(token).getSubject();
    }
    
    @Override
    public String createAccessToken(@NonNull final String email, @NonNull final Role role) {
        final var tokenIssuedAt = Instant.now().truncatedTo(ChronoUnit.MILLIS);
        final var tokenExpiration = tokenIssuedAt.plusMillis(accessTokenValidityInMs);
        final var token = JWT.create()
                .withSubject(email)
                .withClaim(ApplicationConstants.JwtClaimNames.USER_ROLE, role.toString())
                .withIssuedAt(tokenIssuedAt)
                .withExpiresAt(tokenExpiration)
                .sign(signAlgorithm.getSignAlgorithm());
        
        log.info("access token =" + token);
        
        return token;
    }
    
    @Override
    public String createRefreshToken(@NonNull final String email) {
        final var tokenIssuedAt = Instant.now().truncatedTo(ChronoUnit.MILLIS);
        final var tokenExpiration = tokenIssuedAt.plusMillis(refreshTokenValidityInMs);
        final var token = JWT.create()
                .withSubject(email)
                .withIssuedAt(tokenIssuedAt)
                .withExpiresAt(tokenExpiration)
                .sign(signAlgorithm.getSignAlgorithm());
        
        log.info("refresh token =" + token);
        
        return token;
    }
    
    @Override
    public boolean validateRefreshToken(@NonNull final String token, @NonNull final String subject) {
        final var decodedJWT = JWT.require(signAlgorithm.getSignAlgorithm())
                .withSubject(subject)
                .build()
                .verify(token);
        
        var tokenValidityInMs = decodedJWT.getExpiresAtAsInstant().toEpochMilli() -
                decodedJWT.getIssuedAtAsInstant().toEpochMilli();
        
        return tokenValidityInMs == refreshTokenValidityInMs;
    }
    
    @Override
    public boolean validateAccessToken(@NonNull final String token, @NonNull final String email) {
        final var decodedJWT = JWT.require(signAlgorithm.getSignAlgorithm())
                .withClaimPresence(ApplicationConstants.JwtClaimNames.USER_ROLE)
                .build()
                .verify(token);
        
        var tokenValidityInMs = decodedJWT.getExpiresAtAsInstant().toEpochMilli() -
                decodedJWT.getIssuedAtAsInstant().toEpochMilli();
        
        return tokenValidityInMs == accessTokenValidityInMs;
    }
    
    /**
     * Create DecodedJWT object from token.
     * If the token is valid, it returns a DecodedJWT object
     * that can be used to extract certain fields from the token
     * otherwise it throws a JWTVerificationException.
     *
     * @param token the JWT token we will create DecodedJWT from
     * @return DecodedJWT object
     * @see JWTVerificationException
     * @see DecodedJWT
     */
    private DecodedJWT extractDecodedJWT(@NonNull final String token) {
        
        return JWT.require(signAlgorithm.getSignAlgorithm())
                .build()
                .verify(token);
    }
    
    
}
