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
    public class Web {
        
        /**
         * Inner utility class for constants that should be
         */
        @UtilityClass
        public class CookiePaths {
            public static final String REFRESH_TOKEN = "/api/auth/refresh";
            public static final String ACCESS_TOKEN = "/api";
        }
        
        /**
         * Inner utility class for constants related to security part.
         */
        @UtilityClass
        public class DefaultPagingAttributes {
            public static final String PAGE_SIZE = "20";
            public static final String SORT_DIRECTION = "DESC";
            public static final String SORT_FIELD = "id";
        }
        
    }
    
    /**
     * Inner utility class for constants related to security part.
     */
    @UtilityClass
    public class Security {
        
        /**
         * Inner class containing paths that need special security settings.
         */
        @UtilityClass
        public class PublicRoutes {
            public static final String LOGIN_PATH = "/auth/authenticate";
            public static final String TOKEN_REFRESH = "/auth/refresh";
            
        }
    }
    
    /**
     * Inner utility class for constants related to security jwt claims part.
     */
    @UtilityClass
    public class JwtClaimNames {
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
        public static final String ACCESS_TOKEN_EXPIRED = "Access token expired.";
        public static final String BAD_REQUEST = "The request has received an argument that is invalid or inappropriate for this method's purposes.";
    }
}
