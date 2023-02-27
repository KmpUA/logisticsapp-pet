package com.yukon.logistics.model.dto;

import com.yukon.logistics.persistence.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode
@ToString
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticatedUser {
    
    String firstName;
    
    String lastName;
    
    @Enumerated(EnumType.STRING)
    Role role;
    
    String imageUrl;
    
}
