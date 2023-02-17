package com.yukon.logistics.configuration.security.jwt;

import com.yukon.logistics.exceptions.UserNotFoundException;
import com.yukon.logistics.persistence.entity.User;
import com.yukon.logistics.persistence.repository.UserRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtUserServiceImpl implements UserDetailsService {
    
    UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(@NonNull final String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UserNotFoundException("The user cannot be loaded by " +
                        "the given username. The username may be incorrect " +
                        "or requested user doesn't exist"));
        
        return JwtUser.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }
}
