package com.yukon.logistics.repository;

import com.yukon.logistics.persistence.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findById(Long id);
    Optional<City> findByName(String name);
    void deleteById(Long id);
}
