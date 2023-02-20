package com.yukon.logistics.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "dispatchers")
public class Dispatcher extends User {

    @OneToMany(mappedBy = "dispatcher", cascade = CascadeType.ALL)
    List<Trucker> truckers;
}