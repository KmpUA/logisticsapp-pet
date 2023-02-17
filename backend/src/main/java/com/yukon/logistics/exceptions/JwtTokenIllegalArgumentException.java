package com.yukon.logistics.exceptions;

import io.jsonwebtoken.JwtException;

import java.io.Serial;

/**
 * Throws when a JWT token is cannot be parsed.
 */
public class JwtTokenIllegalArgumentException extends JwtException {
    @Serial
    private static final long serialVersionUID = -8855061719918504539L;
    
    public JwtTokenIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public JwtTokenIllegalArgumentException(String message) {
        super(message);
    }
}
