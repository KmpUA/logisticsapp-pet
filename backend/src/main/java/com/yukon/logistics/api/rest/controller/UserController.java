package com.yukon.logistics.api.rest.controller;

import com.yukon.logistics.common.ApplicationConstants;
import com.yukon.logistics.model.dto.PageResponse;
import com.yukon.logistics.model.dto.UserRequest;
import com.yukon.logistics.model.dto.UserResponse;
import com.yukon.logistics.model.mapper.UserMapper;
import com.yukon.logistics.persistence.entity.User;
import com.yukon.logistics.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;

import static java.lang.Long.parseLong;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final UserMapper userMapper;
    
    @GetMapping
    public ResponseEntity<PageResponse<UserResponse>> getAll(
            @RequestParam @NonNull final Integer page,
            @RequestParam(defaultValue = ApplicationConstants.Web.
                    DefaultPagingAttributes.PAGE_SIZE) final Integer size,
            @RequestParam(defaultValue = ApplicationConstants.Web.
                    DefaultPagingAttributes.SORT_DIRECTION) final String sortDirection,
            @RequestParam(defaultValue = ApplicationConstants.Web.
                    DefaultPagingAttributes.SORT_FIELD) final String sortField) {
        
        final var pageRequest = PageRequest.of(page,
                size, Sort.Direction.fromString(sortDirection), sortField);
        
        final var pageUserResponse = userService.findAll(pageRequest).map(userMapper::toResponse);
        
        final var pageResponse = PageResponse.<UserResponse>builder().
                content(pageUserResponse.getContent()).totalElements(pageUserResponse.getTotalElements()).build();
        
        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable("id") String id) {
        UserResponse userResponse = userMapper
                .toResponse(userService.findById(parseLong(id)));
        
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getByEmail(@PathVariable("email") String email) {
        UserResponse userResponse = userMapper
                .toResponse(userService.findByEmail(email));
        
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") String id) {
        userService.deleteUserById(parseLong(id));
        
        return ResponseEntity.ok("Entity successfully deleted.");
    }
    
    @PostMapping
    public ResponseEntity<UserResponse> add(@RequestBody UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        UserResponse userResponse = userMapper.toResponse(userService.addUser(user));
        
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
    
    @GetMapping("/available/{email}")
    public ResponseEntity<Boolean> checkEmailAvailability(@PathVariable("email") String email) {
        Boolean isAvailable = userService.checkEmailAvailability(email);
        
        return new ResponseEntity<>(isAvailable, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable("id") String id,
                                               @RequestBody UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        user.setId(parseLong(id));

        if(userRequest.getPassword() == null) {
            user.setPassword(userService.findById(parseLong(id)).getPassword());
        }

        user.setCreated(userService.findById(parseLong(id)).getCreated());
        UserResponse userResponse = new UserMapper().toResponse(userService.updateUser(user));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
