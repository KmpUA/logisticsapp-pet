package com.yukon.logistics.model.mapper;

import com.yukon.logistics.model.dto.TruckRequest;
import com.yukon.logistics.model.dto.TruckResponse;
import com.yukon.logistics.persistence.entity.Truck;

import java.util.ArrayList;
import java.util.List;

public class TruckMapper {
    public TruckResponse toResponse(Truck truck) {
        return TruckResponse.builder().id(truck.getId())
                .model(truck.getModel())
                .locationCityId(truck.getLocation().getId())
                .fuelConsumption(truck.getFuelConsumption())
                .orderCapacity(truck.getOrderCapacity())
                .spaceCapacity(truck.getSpaceCapacity())
                .condition(truck.getCondition())
                .licensePlate(truck.getLicensePlate())
                .truckerId(truck.getTrucker().getId()).build();
    }

    public List<TruckResponse> toListResponse(List<Truck> trucks) {
        List<TruckResponse> response = new ArrayList<>();
        for(Truck truck : trucks) {
            response.add(toResponse(truck));
        }
        return response;
    }

    public Truck toEntity(TruckRequest truckRequest) {
        Truck truck = new Truck();
        truck.setCondition(truckRequest.getCondition());
        truck.setModel(truckRequest.getModel());
        truck.setFuelConsumption(truckRequest.getFuelConsumption());
        truck.setOrderCapacity(truckRequest.getOrderCapacity());
        truck.setSpaceCapacity(truckRequest.getSpaceCapacity());
        truck.setVinCode(truckRequest.getVinCode());
        truck.setLicensePlate(truckRequest.getLicensePlate());
        return truck;
    }
}
