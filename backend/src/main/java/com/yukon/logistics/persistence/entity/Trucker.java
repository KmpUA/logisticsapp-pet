package com.yukon.logistics.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "trucker")
public class Trucker extends User {

    @OneToOne
    @JoinColumn(name = "truck_id", referencedColumnName = "id")
    Truck truck;

    @ManyToOne(fetch = FetchType.LAZY)
    Dispatcher dispatcher;

    @OneToMany(mappedBy = "trucker")
    List<Order> orders;
}
