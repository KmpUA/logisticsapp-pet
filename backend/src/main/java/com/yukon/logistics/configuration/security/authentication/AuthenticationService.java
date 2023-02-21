package com.yukon.logistics.configuration.security.authentication;

import com.yukon.logistics.configuration.security.jwt.JwtService;
import com.yukon.logistics.exceptions.UserNotFoundException;
import com.yukon.logistics.model.mapper.UserMapper;
import com.yukon.logistics.persistence.repository.UserRepository;
import lombok.AccessLevel;
import lombok.NonNull;
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
    JwtService jwtService;
    UserMapper userMapper;
    
    public AuthenticationResponse authenticate(@NonNull final AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()));
        
        final var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Failed to"
                        + " authenticate the user. User is not found"));
        
        final var token = jwtService.createToken(user.getEmail(), user.getRole());
        
        return AuthenticationResponse.builder()
                .token(token).userResponse(userMapper.toResponse(user)).build();
    }
}
