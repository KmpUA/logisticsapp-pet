package com.yukon.logistics.repository;

import com.yukon.logistics.persistence.entity.Order;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@NonNullApi
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long id);
    Optional<Order> findByFrom(String city);
    Optional<Order> findByTo(String city);
    Optional<Order> findByTrucker(String truckerName);
    void deleteById(Long id);
}
