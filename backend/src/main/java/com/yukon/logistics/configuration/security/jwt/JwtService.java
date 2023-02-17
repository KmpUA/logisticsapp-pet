package com.yukon.logistics.configuration.security.jwt;

import com.yukon.logistics.persistence.entity.Role;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    
    /**
     * Extracts and parses the JWT contained in the given HttpServletRequest.
     *
     * @param request the HttpServletRequest to extract the JWT from
     * @return extracted and parsed JWT from request
     */
    String getJwtFromRequest(@NonNull final HttpServletRequest request);
    
    /**
     * Returns the user email contained in the given JWT token as a subject.
     *
     * @param token the JWT to extract the user email from
     * @return username
     */
    String extractUsername(@NonNull final String token);
    
    /**
     * Creates a new JWT with the given username and roles.
     *
     * @param email the username to include in the token
     * @param role  the set of roles to include in the token
     * @return username
     */
    String createToken(@NonNull final String email, @NonNull final Role role);
    
    /**
     * Checks if the token is valid
     *
     * @param token the JWT we will validate
     * @return true if token valid and false otherwise
     */
    boolean isTokenValid(@NonNull final String token, @NonNull final UserDetails userDetails);
}
