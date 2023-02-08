package com.yukon.logistics.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    Long id;
    @ManyToOne
    City from;
    @ManyToOne
    City to;
    String cargoDescription;
    double cargoWeight;
    //private Trucker trucker;
    @CreatedDate
    LocalDateTime created;
    @LastModifiedDate
    LocalDateTime modify;
    LocalDateTime startDeliver;
    LocalDateTime endDeliver;
}
