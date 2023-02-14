package com.yukon.logistics.persistence.repository;

import com.yukon.logistics.persistence.entity.User;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@NonNullApi
public interface UserRepository extends JpaRepository<User, Long>  {
    @Override
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
