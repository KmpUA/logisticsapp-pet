package com.yukon.logistics.service.impl;

import com.yukon.logistics.persistence.entity.Trucker;
import com.yukon.logistics.persistence.repository.TruckerRepository;
import com.yukon.logistics.service.TruckerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TruckerServiceImpl implements TruckerService {

    private final TruckerRepository truckerRepository;

    public TruckerServiceImpl(TruckerRepository truckerRepository) {
        super();
        this.truckerRepository = truckerRepository;
    }

    @Override
    public Trucker addTrucker(Trucker trucker) {
        return truckerRepository.save(trucker);
    }

    @Override
    public Trucker findTruckerById(Long id) {
        return truckerRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Trucker> findAllTruckers() {
        return truckerRepository.findAll();
    }

    @Override
    public Trucker updateTrucker(Trucker trucker) {
        return truckerRepository.save(trucker);
    }

    @Override
    public Trucker findTruckerByTruck(Long id) {
        return truckerRepository.findByTruck(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Trucker> findTruckerByDispatcher(Long id) {
        return truckerRepository.findByDispatcher(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Trucker findTruckerByOrder(Long id) {
        return truckerRepository.findByOrders(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteTruckerById(Long id) {
        truckerRepository.deleteById(id);
    }
}
