package com.yukon.logistics.service.impl;

import com.yukon.logistics.persistence.entity.User;
import com.yukon.logistics.persistence.repository.UserRepository;
import com.yukon.logistics.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    public final UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }
    
    @Override
    public Page<User> findAll(Pageable pageable) {
        
        return userRepository.findAll(pageable);
    }
    
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Boolean checkEmailAvailability(String email) {
        return !userRepository.existsByEmail(email);
    }
}
