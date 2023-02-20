package com.yukon.logistics.service;

import com.yukon.logistics.persistence.entity.Trucker;

import java.util.List;

public interface TruckerService {
    Trucker addTrucker(Trucker trucker);
    Trucker findTruckerById(Long id);
    List<Trucker> findAllTruckers();
    Trucker updateTrucker(Trucker trucker);
    Trucker findTruckerByTruck(Long id);
    List<Trucker> findTruckerByDispatcher(Long id);
    Trucker findTruckerByOrder(Long id);
    void deleteTruckerById(Long id);

}
