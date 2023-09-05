package com.yukon.logistics.configuration.security.jwt;

import com.yukon.logistics.persistence.entity.Role;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;

public interface JwtService {
    
    /**
     * Extracts and parses the access token contained in the given HttpServletRequest.
     *
     * @param request the HttpServletRequest to extract the JWT from
     * @return extracted and parsed JWT from request
     */
    String getAccessTokenFromRequest(@NonNull final HttpServletRequest request);
    
    /**
     * Extracts and parses the refresh token contained in the given HttpServletRequest.
     *
     * @param request the HttpServletRequest to extract the JWT from
     * @return extracted and parsed JWT from request
     */
    String getRefreshTokenFromRequest(@NonNull final HttpServletRequest request);
    
    /**
     * Returns the user email contained in the given JWT as a subject.
     *
     * @param token the JWT to extract the user email from
     * @return username
     */
    String extractSubject(@NonNull final String token);
    
    /**
     * Creates a new access token with the given username and roles.
     *
     * @param email the user email to include in the token
     * @param role  the role to include in the token
     * @return access token
     */
    String createAccessToken(@NonNull final String email,
                             @NonNull final Role role);
    
    /**
     * Creates a new refresh token.
     *
     * @param email the user email to include in the token
     * @return refresh token
     */
    String createRefreshToken(@NonNull final String email);
    
    /**
     * Validates refresh token
     *
     * @param token   refresh token which must be validated
     * @param subject the subject that must contain the token
     * @return true if valid and false otherwise
     */
    boolean validateRefreshToken(@NonNull final String token, @NonNull final String subject);
    
    /**
     * Validates access token
     *
     * @param token access token which must be validated
     * @param email user email token must contain
     * @return true if valid and false otherwise
     */
    boolean validateAccessToken(@NonNull final String token, @NonNull final String email);
    
}
