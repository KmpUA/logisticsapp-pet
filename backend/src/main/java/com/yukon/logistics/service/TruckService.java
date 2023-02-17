package com.yukon.logistics.service;

import com.yukon.logistics.persistence.entity.Truck;
import java.util.List;

public interface TruckService {
    Truck addTruck(Truck truck);
    List<Truck> findAllTrucks();
    Truck updateTruck(Truck truck);
    Truck findTruckById(Long id);
    Truck findByLicensePlate(String licensePlate);
    Truck findTruckByVinCode(String vinCode);
    List<Truck> findAllTrucksByModel(String model);
    void deleteTruckById(Long id);
}
