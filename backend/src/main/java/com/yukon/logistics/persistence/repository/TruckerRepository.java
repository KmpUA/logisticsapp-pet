package com.yukon.logistics.persistence.repository;

import com.yukon.logistics.persistence.entity.Trucker;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@NonNullApi
public interface TruckerRepository extends JpaRepository<Trucker, Long> {
    Optional<Trucker> findByTruck(Long id);
    Optional<List<Trucker>> findByDispatcher(Long id);
    Optional<Trucker> findByOrders(Long id);
    void deleteById(Long id);
}
