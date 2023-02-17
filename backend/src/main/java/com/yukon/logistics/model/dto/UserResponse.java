package com.yukon.logistics.model.dto;

import com.yukon.logistics.persistence.entity.Role;
import com.yukon.logistics.persistence.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    Long id;
    String firstName;
    String lastName;
    String email;
    String imageUrl;
    String phone;
    Role role;
    Status status;
}