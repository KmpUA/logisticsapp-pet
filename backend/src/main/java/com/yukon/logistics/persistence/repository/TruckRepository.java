package com.yukon.logistics.persistence.repository;

import com.yukon.logistics.persistence.entity.Truck;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@NonNullApi
public interface TruckRepository extends JpaRepository<Truck, Long> {

    @Override
    Optional<Truck> findById(Long id);
    Optional<Truck> findByLicensePlate(String licensePlate);
    Optional<Truck> findByVinCode(String vinCode);
    List<Truck> findAllByModel(String model);
    void deleteById(Long id);
}
