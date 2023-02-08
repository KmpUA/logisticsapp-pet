package com.yukon.logistics.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Trucker extends User{
    @OneToOne
    Truck track;
    @ManyToOne
    Dispatcher dispatcher;
    @OneToMany
    List<Order> orders;
}
