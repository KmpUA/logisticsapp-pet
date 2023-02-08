package com.yukon.logistics.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.io.Serializable;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Truck implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    Long id;
    String model;
    @ManyToOne
    City location;
    double fuelConsumption;
    double orderCapacity;
    double spaceCapacity;
    Condition condition;
    String vinCode;
    String licensePlate;
    @OneToOne
    Trucker trucker;
}
