package com.yukon.logistics.persistence.repository;

import com.yukon.logistics.persistence.entity.Dispatcher;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@NonNullApi
public interface DispatcherRepository extends JpaRepository<Dispatcher, Long> {
    Optional<Dispatcher> findByTruckers(Long id);
    void deleteById(Long id);
}
