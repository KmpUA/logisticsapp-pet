package com.yukon.logistics.persistence.repository;

import com.yukon.logistics.persistence.entity.Order;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@NonNullApi
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long id);
    Optional<Order> findByFromName(String city);
    Optional<Order> findByToName(String city);
    List<Order> findAllByTruckerId(Long truckerId);
    void deleteById(Long id);
}
