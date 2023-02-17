package com.yukon.logistics.configuration.security.jwt;

import com.yukon.logistics.common.ApplicationConstants;
import com.yukon.logistics.exceptions.JwtTokenIllegalArgumentException;
import com.yukon.logistics.persistence.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JwtServiceImpl implements JwtService {
    
    @NonFinal
    @Value("${spring.security.jwtValidityInMs}")
    long jwtValidityInMs;
    
    SecretKeyProvider secretKeyProvider;
    
    @Override
    @Nullable
    public String getJwtFromRequest(@NonNull final HttpServletRequest request) {
        final var header = request.getHeader(ApplicationConstants.Security.TOKEN_HEADER_NAME);
        final var tokenPrefix = ApplicationConstants.Security.TOKEN_PREFIX;
        
        if (Objects.nonNull(header)
                && header.startsWith(tokenPrefix)) {
            
            return header.substring(tokenPrefix.length());
        }
        
        log.warn("Unable resolve token" +
                " from request. It can be null or the prefix is missing.");
        
        return null;
    }
    
    @Override
    public String extractUsername(@NonNull final String token) {
        return extractAllClaims(token).getSubject();
    }
    
    @Override
    public String createToken(@NonNull final String email, @NonNull final Role role) {
        final var jwtIssuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        final var jwtExpiration = jwtIssuedAt.plusMillis(jwtValidityInMs);
        
        final var claims = Jwts.claims()
                .setSubject(email)
                .setIssuedAt(Date.from(jwtIssuedAt))
                .setExpiration(Date.from(jwtExpiration));
        claims.put(ApplicationConstants.JwtClaims.USER_ROLE, role);
        
        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKeyProvider.getEncodedSecret())
                .compact();
    }
    
    @Override
    public boolean isTokenValid(@NonNull final String token, @NonNull final UserDetails userDetails) {
        final var username = extractUsername(token);
        
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    
    /**
     * Check if the passed token is expired.
     *
     * @param token the JWT token we will validate
     * @return true if token expired and false otherwise
     */
    private boolean isTokenExpired(@NonNull final String token) {
        final var tokenExpiration = extractAllClaims(token).getExpiration();
        
        return tokenExpiration.before(Date.from(Instant.now()));
    }
    
    /**
     * Extract claims from token.
     * It can be used for extract specific fields from the token
     *
     * @param token the JWT token we will extract claims from
     * @return extracted claims from token
     */
    private Claims extractAllClaims(@NonNull final String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(secretKeyProvider.getEncodedSecret())
                    .build().parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            throw new JwtTokenIllegalArgumentException(
                    "An error occurred while parsing the provided JWT token. "
                            + "It may be expired or invalid.", e);
        }
    }
}
