package com.yukon.logistics.configuration.security.authentication;

import com.yukon.logistics.configuration.security.jwt.JwtServiceImpl;
import com.yukon.logistics.exceptions.UserNotFoundException;
import com.yukon.logistics.persistence.entity.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    
    UserRepository userRepository;
    AuthenticationManager authenticationManager;
    JwtServiceImpl jwtService;
    
    public AuthenticationResponse authenticate(final AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        
        final var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException("Failed to" + " authenticate the user. User is not found"));
        
        final var token = jwtService.createToken(user.getEmail(), user.getRole());
        
        return AuthenticationResponse.builder().token(token).build();
    }
}
