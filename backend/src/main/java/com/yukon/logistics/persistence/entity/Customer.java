package com.yukon.logistics.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "customers")
public class Customer extends User {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id")
    List<Order> orders;
}
