package com.yukon.logistics.common;

import lombok.experimental.UtilityClass;

/**
 * Contains various constants used in the application.
 */
@UtilityClass
public class ApplicationConstants {
    
    /**
     * Inner utility class for constants related to security part.
     */
    @UtilityClass
    public class Security {
        public static final String TOKEN_PREFIX = "Bearer ";
        public static final String TOKEN_HEADER_NAME = "Authorization";
        
        /**
         * Inner class containing paths that need special security settings.
         */
        @UtilityClass
        public class Routing {
            public static final String LOGIN_PATH = "/auth/authenticate";
        }
    }
    
    /**
     * Inner utility class for constants related to security jwt claims part.
     */
    @UtilityClass
    public class JwtClaims {
        public static final String USER_ROLE = "role";
    }
    
    @UtilityClass
    public class DataValidation {
        public static final int MIN_SIZE_OF_EMAIL = 5;
        public static final int MAX_SIZE_OF_EMAIL = 100;
        public static final int MIN_SIZE_OF_PASSWORD = 6;
        public static final int MAX_SIZE_OF_PASSWORD = 255;
    }
    
    @UtilityClass
    public class ErrorMassage {
        public static final String UNAUTHORIZED_ERROR_MESSAGE = "The user does not have the necessary permissions or authorization.";
        public static final String UNKNOWN_ERROR_MESSAGE = "An unknown error has occurred.";
        public static final String BAD_REQUEST = "The request has received an argument that is invalid or inappropriate for this method's purposes.";
    }
}
