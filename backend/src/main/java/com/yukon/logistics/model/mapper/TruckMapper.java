package com.yukon.logistics.model.mapper;

import com.yukon.logistics.model.dto.TruckRequest;
import com.yukon.logistics.model.dto.TruckResponse;
import com.yukon.logistics.persistence.entity.Truck;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TruckMapper {
    public TruckResponse toResponse(Truck truck) {
        Long locationCityId = truck.getLocation() != null ? truck.getLocation().getId() : null;
        Long truckerId = truck.getTrucker() != null ? truck.getTrucker().getId() : null;
        return TruckResponse.builder().id(truck.getId())
                .model(truck.getModel())
                .locationCityId(locationCityId)
                .fuelConsumption(truck.getFuelConsumption())
                .orderCapacity(truck.getOrderCapacity())
                .spaceCapacity(truck.getSpaceCapacity())
                .condition(truck.getCondition())
                .licensePlate(truck.getLicensePlate())
                .truckerId(truckerId).build();
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
