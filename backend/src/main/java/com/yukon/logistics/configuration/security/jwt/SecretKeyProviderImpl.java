package com.yukon.logistics.configuration.security.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
@Getter
public class SecretKeyProviderImpl implements SecretKeyProvider {
    
    private final Key encodedSecret;
    
    public SecretKeyProviderImpl() {
        this.encodedSecret = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
}
