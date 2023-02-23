package com.yukon.logistics.api.rest.controller;

import com.yukon.logistics.model.dto.CustomerRequest;
import com.yukon.logistics.model.dto.CustomerResponse;
import com.yukon.logistics.model.mapper.CustomerMapper;
import com.yukon.logistics.persistence.entity.Customer;
import com.yukon.logistics.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Long.parseLong;

@Controller
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping()
    @Transactional
    public ResponseEntity<List<CustomerResponse>> getAll(boolean includeOrders) {
        List<CustomerResponse> customerResponseList = new CustomerMapper()
                .toListResponse(customerService.findAllCustomers(), includeOrders);
        return new ResponseEntity<>(customerResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable("id") String id) {
        CustomerResponse customerResponse = new CustomerMapper()
                .toResponse(customerService.findCustomerById(parseLong(id)), true);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest customerRequest){
        Customer customer = new CustomerMapper().toEntity(customerRequest);
        CustomerResponse customerResponse = new CustomerMapper()
                .toResponse(customerService.addCustomer(customer), false);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable("id") String id,
                                                           @RequestBody CustomerRequest customerRequest){
        Customer customer = new CustomerMapper().toEntity(customerRequest);
        customer.setId(parseLong(id));
        customer.setOrders(customerService.findCustomerById(parseLong(id)).getOrders());
        CustomerResponse customerResponse = new CustomerMapper()
                .toResponse(customerService.updateCustomer(customer), false);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id){
        customerService.deleteCustomerById(parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
