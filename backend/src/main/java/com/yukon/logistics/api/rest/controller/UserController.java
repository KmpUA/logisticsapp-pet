package com.yukon.logistics.api.rest.controller;

import com.yukon.logistics.model.dto.UserResponse;
import com.yukon.logistics.model.mapper.UserMapper;
import com.yukon.logistics.persistence.entity.User;
import com.yukon.logistics.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import static java.lang.Long.parseLong;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAll() {
        List<UserResponse> response = new UserMapper().toListResponse(userService.findAllUsers());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable("id") String id) {
        UserResponse userResponse = new UserMapper().toResponse(userService.findById(parseLong(id)));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> getByEmail(@PathVariable("email") String email) {
        UserResponse userResponse = new UserMapper().toResponse(userService.findByEmail(email));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponse> add(@RequestBody User user) {
        UserResponse userResponse = new UserMapper().toResponse(userService.addUser(user));
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/available/{email}")
    public ResponseEntity<Boolean> checkEmailAvailability(@PathVariable("email") String email) {
        Boolean isAvailable = userService.checkEmailAvailability(email);
        return new ResponseEntity<>(isAvailable, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> update(@RequestBody User user) {
        UserResponse userResponse = new UserMapper().toResponse(userService.updateUser(user));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
