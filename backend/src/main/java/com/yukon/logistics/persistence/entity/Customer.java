package com.yukon.logistics.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "customers")
public class Customer extends User {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="customer")
    List<Order> orders;
}
