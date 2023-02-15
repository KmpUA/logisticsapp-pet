package com.yukon.logistics.repository;

import com.yukon.logistics.persistence.entity.Customer;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@NonNullApi
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findById(Long id);
    Optional<Customer> findByOrders(Long id);

    void deleteById(Long id);
}
