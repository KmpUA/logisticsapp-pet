package com.yukon.logistics.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

import lombok.*;
import lombok.experimental.FieldDefaults;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "city_id_from", nullable = false)
    City from;

    @ManyToOne
    @JoinColumn(name = "city_id_to", nullable = false)
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
