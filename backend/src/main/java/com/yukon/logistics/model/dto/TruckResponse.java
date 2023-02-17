package com.yukon.logistics.model.dto;

import com.yukon.logistics.persistence.entity.Condition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TruckResponse {
    private Long id;
    private String model;
    private Long locationCityId;
    private double fuelConsumption;
    private double orderCapacity;
    private double spaceCapacity;
    private Condition condition;
    private String licensePlate;
    private Long truckerId;
}
