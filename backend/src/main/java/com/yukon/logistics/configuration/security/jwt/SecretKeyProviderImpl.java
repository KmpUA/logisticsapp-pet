package com.yukon.logistics.configuration.security.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
@Getter
public class SecretKeyProviderImpl implements SecretKeyProvider {
    
    private final Key encodedSecret;
    
    public SecretKeyProviderImpl(@Value("${spring.security.jwtSecret}") final String secretKey) {
        encodedSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}
