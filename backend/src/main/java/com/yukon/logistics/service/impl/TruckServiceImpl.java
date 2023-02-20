package com.yukon.logistics.service.impl;

import com.yukon.logistics.persistence.entity.Truck;
import com.yukon.logistics.persistence.repository.TruckRepository;
import com.yukon.logistics.service.TruckService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TruckServiceImpl implements TruckService {
    public final TruckRepository truckRepository;

    @Override
    public Truck addTruck(Truck truck) {
        return truckRepository.save(truck);
    }

    @Override
    public List<Truck> findAllTrucks() {
        return truckRepository.findAll();
    }

    @Override
    public Truck updateTruck(Truck truck) {
        return truckRepository.save(truck);
    }

    @Override
    public Truck findTruckById(Long id) {
        return truckRepository.findById(id).orElseThrow();
    }

    @Override
    public Truck findByLicensePlate(String licensePlate) {
        return truckRepository.findByLicensePlate(licensePlate).orElseThrow();
    }

    @Override
    public Truck findTruckByVinCode(String vinCode) {
        return truckRepository.findByVinCode(vinCode).orElseThrow();
    }

    @Override
    public List<Truck> findAllTrucksByModel(String model) {
        return truckRepository.findAllByModel(model);
    }

    @Override
    public void deleteTruckById(Long id) {
        truckRepository.deleteById(id);
    }
}
