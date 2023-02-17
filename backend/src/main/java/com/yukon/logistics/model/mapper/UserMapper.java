package com.yukon.logistics.model.mapper;

import com.yukon.logistics.model.dto.UserRequest;
import com.yukon.logistics.model.dto.UserResponse;
import com.yukon.logistics.persistence.entity.User;

import java.util.ArrayList;
import java.util.List;

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
        for(User user : users) {
            response.add(toResponse(user));
        }
        return response;
    }

    public User toEntity (UserRequest userRequest) {
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
}
