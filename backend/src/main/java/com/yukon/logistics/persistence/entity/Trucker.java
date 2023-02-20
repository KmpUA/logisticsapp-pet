package com.yukon.logistics.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "truckers")
public class Trucker extends User {

    @OneToOne
    @JoinColumn(name = "truck_id", referencedColumnName = "id")
    Truck truck;

    @ManyToOne(fetch = FetchType.LAZY)
    Dispatcher dispatcher;

    @OneToMany(mappedBy = "trucker")
    List<Order> orders;
}
