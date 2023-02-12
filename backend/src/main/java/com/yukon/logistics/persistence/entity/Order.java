package com.yukon.logistics.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "city_idfrom", nullable = false)
    City from;

    @ManyToOne
    @JoinColumn(name = "city_idto", nullable = false)
    City to;

    @Column(name = "cargo_description")
    String cargoDescription;

    @Column(name = "cargo_weight", nullable = false)
    double cargoWeight;

    @ManyToOne(fetch = FetchType.LAZY)
    Trucker trucker;

    @CreatedDate
    @Column(name = "created")
    LocalDateTime created;

    @LastModifiedDate
    @Column(name = "modify")
    LocalDateTime modify;

    @Column(name = "start_deliver")
    LocalDateTime startDeliver;

    @Column(name = "end_deliver")
    LocalDateTime endDeliver;
}
