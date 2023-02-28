package com.yukon.logistics.configuration.security.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtSignProvider {
    Algorithm signAlgorithm;
    
    public JwtSignProvider(@Value("${spring.security.jwt.secret}") final String jwtSecretKey) {
        signAlgorithm = Algorithm.HMAC256(jwtSecretKey);
    }
}
