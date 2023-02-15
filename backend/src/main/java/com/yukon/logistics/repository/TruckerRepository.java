package com.yukon.logistics.repository;

import com.yukon.logistics.persistence.entity.Trucker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TruckerRepository extends JpaRepository<Trucker, Long> {
    Optional<Trucker> findByTruck(String id);
    Optional<List<Trucker>> findByDispatcher(String id);
    Optional<Trucker> findByOrders(String id);
    void deleteById(Long id);
}
