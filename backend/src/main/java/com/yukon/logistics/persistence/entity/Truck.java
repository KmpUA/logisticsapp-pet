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

    @Column(name = "model")
    String model;

    @ManyToOne
    @JoinColumn(name = "city_id")
    City location;

    @Column(name = "fuel_consuption")
    double fuelConsumption;

    @Column(name = "order_capacity")
    double orderCapacity;

    @Column(name = "space_capacity")
    double spaceCapacity;

    @Column(name = "condition")
    Condition condition;

    @Column(name = "vin_code")
    String vinCode;

    @Column(name = "license_place")
    String licensePlate;

    @OneToOne(mappedBy = "truck", cascade = CascadeType.ALL, orphanRemoval = true)
    Trucker trucker;
}
