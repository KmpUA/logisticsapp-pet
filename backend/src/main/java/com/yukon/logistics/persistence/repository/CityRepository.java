package com.yukon.logistics.persistence.repository;

import com.yukon.logistics.persistence.entity.City;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@NonNullApi
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findById(Long id);
    Optional<City> findByName(String name);
    void deleteById(Long id);
}
