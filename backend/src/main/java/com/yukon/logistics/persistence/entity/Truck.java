package com.yukon.logistics.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "truck")
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "model", nullable = false)
    String model;

    @ManyToOne
    @JoinColumn(name = "city_id")
    City location;

    @Column(name = "fuel_consuption", nullable = false)
    double fuelConsumption;

    @Column(name = "order_capacity", nullable = false)
    double orderCapacity;

    @Column(name = "space_capacity", nullable = false)
    double spaceCapacity;

    @Column(name = "condition", nullable = false)
    @Enumerated(EnumType.STRING)
    Condition condition;

    @Column(name = "vin_code", nullable = false)
    String vinCode;

    @Column(name = "license_place", nullable = false)
    String licensePlate;

    @OneToOne(mappedBy = "truck", cascade = CascadeType.ALL, orphanRemoval = true)
    Trucker trucker;
}
