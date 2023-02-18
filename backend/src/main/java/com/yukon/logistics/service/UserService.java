package com.yukon.logistics.service;

import com.yukon.logistics.persistence.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User addUser(User user);
    Page<User> findOnePage(Pageable pageable);
    List<User> findAllUsers();
    User updateUser(User user);
    User findById(Long id);
    User findByEmail(String email);
    void deleteUserById(Long id);
    Boolean checkEmailAvailability (String email);
}
