package com.yukon.logistics.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @Column(name = "first_name", nullable = false)
    String firstName;
    
    @Column(name = "last_name", nullable = false)
    String lastName;
    
    @Column(name = "email", nullable = false)
    String email;
    
    @Column(name = "password", nullable = false)
    String password;
    
    @Column(name = "image_url")
    String imageUrl;
    
    @Column(name = "phone", nullable = false)
    String phone;
    
    @CreatedDate
    @Column(name = "created")
    Instant created;
    
    @LastModifiedDate
    @Column(name = "updated")
    LocalDateTime updated;
    
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    Role role;
    
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    Status status;
}
