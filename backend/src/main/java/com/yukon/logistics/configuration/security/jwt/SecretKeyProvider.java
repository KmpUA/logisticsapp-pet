package com.yukon.logistics.configuration.security.jwt;

import java.security.Key;

/**
 * An interface for providing encoded secrets.
 */
public interface SecretKeyProvider {
    
    /**
     * Returns an encoded secret.
     *
     * @return the encoded secret
     */
    Key getEncodedSecret();
}
