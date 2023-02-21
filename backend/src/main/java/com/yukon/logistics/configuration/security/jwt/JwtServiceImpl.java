package com.yukon.logistics.configuration.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yukon.logistics.common.ApplicationConstants;
import com.yukon.logistics.persistence.entity.Role;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Service
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JwtServiceImpl implements JwtService {
    
    long jwtValidityInMs;
    Algorithm signAlgorithm;
    
    public JwtServiceImpl(
            @Value("${spring.security.jwtValidityInMs}") long jwtValidityInMs,
            @Value("${spring.security.jwtSecret}") String jwtSecretKey) {
        this.jwtValidityInMs = jwtValidityInMs;
        signAlgorithm = Algorithm.HMAC256(jwtSecretKey);
    }
    
    @Override
    @Nullable
    public String getJwtFromRequest(@NonNull final HttpServletRequest request) {
        final var header = request.getHeader(ApplicationConstants.Security.TOKEN_HEADER_NAME);
        
        if (Objects.nonNull(header)
                && header.startsWith(ApplicationConstants.Security.TOKEN_PREFIX)) {
            
            return header.replace(ApplicationConstants.Security.TOKEN_PREFIX, "");
        }
        
        log.warn("Unable resolve token" +
                " from request. It can be null or the prefix is missing.");
        
        return null;
    }
    
    @Override
    public String extractUsername(@NonNull final String token) {
        return extractDecodedJWT(token).getSubject();
    }
    
    @Override
    public String createToken(@NonNull final String email, @NonNull final Role role) {
        final var jwtIssuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        final var jwtExpiration = jwtIssuedAt.plusMillis(jwtValidityInMs);
        
        return JWT.create()
                .withSubject(email)
                .withClaim(ApplicationConstants.JwtClaims.USER_ROLE, role.toString())
                .withIssuedAt(jwtIssuedAt)
                .withExpiresAt(jwtExpiration)
                .sign(signAlgorithm);
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
     */
    private DecodedJWT extractDecodedJWT(@NonNull final String token) {
        return JWT.require(signAlgorithm)
                .build()
                .verify(token);
    }
}
