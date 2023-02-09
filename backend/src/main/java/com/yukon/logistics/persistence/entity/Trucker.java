package com.yukon.logistics.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
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
public class Trucker extends User{
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "track_id", referencedColumnName = "id")
    Truck track;

    @ManyToOne(fetch = FetchType.LAZY)
    Dispatcher dispatcher;

    @OneToMany(mappedBy = "trucker", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Order> orders;
}
