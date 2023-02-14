package com.yukon.logistics.api.rest.controller;

import com.yukon.logistics.model.dto.CustomerResponse;
import com.yukon.logistics.model.mapper.CustomerMapper;
import com.yukon.logistics.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static java.lang.Long.parseLong;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponse>> getAll() {
        List<CustomerResponse> customerResponseList = new CustomerMapper().toListResponse(customerService.findAllCustomers());
        return new ResponseEntity<>(customerResponseList, HttpStatus.OK);
    }

    @GetMapping("/order/{order_id}")
    public ResponseEntity<CustomerResponse> getCustomerByOrder(@PathVariable("order_id") String id) {
        CustomerResponse customerResponse = new CustomerMapper().toResponse(customerService.findCustomerByOrder(parseLong(id)));
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable("id") String id) {
        CustomerResponse customerResponse = new CustomerMapper().toResponse(customerService.findCustomerById(parseLong(id)));
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }
}
