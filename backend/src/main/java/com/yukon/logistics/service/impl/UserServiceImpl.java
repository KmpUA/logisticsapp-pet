package com.yukon.logistics.service.impl;

import com.yukon.logistics.persistence.entity.User;
import com.yukon.logistics.persistence.repository.UserRepository;
import com.yukon.logistics.service.PageableService;
import com.yukon.logistics.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService, PageableService<User> {
    public final UserRepository userRepository;
    public final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User addUser(@NonNull final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
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
    public User updateUser(@NonNull final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
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
