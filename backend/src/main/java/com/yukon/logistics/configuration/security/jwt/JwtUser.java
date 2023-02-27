package com.yukon.logistics.configuration.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yukon.logistics.persistence.entity.Role;
import com.yukon.logistics.persistence.entity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtUser implements UserDetails {
    
    String firstName;
    
    String lastName;
    
    String email;
    
    @JsonIgnore
    String password;
    
    @Enumerated(EnumType.STRING)
    Status status;
    
    @Enumerated(EnumType.STRING)
    Role role;
    
    String imageUrl;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public String getUsername() {
        return email;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return status.equals(Status.ACTIVE);
    }
}
