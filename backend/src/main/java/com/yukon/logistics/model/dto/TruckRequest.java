package com.yukon.logistics.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TruckRequest {
    //private Long id;
    private Condition condition;
    String model;
    double fuelConsumption;
    double orderCapacity;
    double spaceCapacity;
    String vinCode;
    String licensePlate;
}
