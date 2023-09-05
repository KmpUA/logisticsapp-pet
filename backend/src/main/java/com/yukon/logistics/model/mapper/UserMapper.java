package com.yukon.logistics.model.mapper;

import com.yukon.logistics.configuration.security.jwt.JwtUser;
import com.yukon.logistics.model.dto.AuthenticatedUser;
import com.yukon.logistics.model.dto.UserRequest;
import com.yukon.logistics.model.dto.UserResponse;
import com.yukon.logistics.persistence.entity.User;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class UserMapper {
    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .phone(user.getPhone())
                .role(user.getRole())
                .status(user.getStatus()).build();
    }
    
    public List<UserResponse> toListResponse(List<User> users) {
        List<UserResponse> response = new ArrayList<>();
        for (User user : users) {
            response.add(toResponse(user));
        }
        return response;
    }
    
    public User toEntity(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setImageUrl(userRequest.getImageUrl());
        user.setPhone(userRequest.getPhone());
        user.setRole(userRequest.getRole());
        user.setStatus(userRequest.getStatus());
        return user;
    }
    
    /**
     * Mapping JwtUser to AuthenticatedUser.
     * It can be used when authenticating a user
     *
     * @param user we will map
     * @return AuthenticatedUser
     */
    public AuthenticatedUser toAuthenticatedUser(@NonNull final JwtUser user) {
        return AuthenticatedUser.builder()
                .id(user.getId())
                .status(user.getStatus())
                .email(user.getEmail())
                .phone(user.getPhone())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .imageUrl(user.getImageUrl())
                .role(user.getRole())
                .build();
    }
}
