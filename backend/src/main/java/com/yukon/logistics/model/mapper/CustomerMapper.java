package com.yukon.logistics.model.mapper;

import com.yukon.logistics.model.dto.CustomerRequest;
import com.yukon.logistics.model.dto.CustomerResponse;
import com.yukon.logistics.persistence.entity.Customer;
import com.yukon.logistics.persistence.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {
    public CustomerResponse toResponse(Customer customer) {
        List<Long> orders = new ArrayList<>();
        for(Order order: customer.getOrders()){
            orders.add(order.getId());
        }
        return CustomerResponse.builder().ordersId(orders)
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .imageUrl(customer.getImageUrl())
                .phone(customer.getPhone())
                .role(customer.getRole())
                .status(customer.getStatus())
                .build();
    }

    public List<CustomerResponse> toListResponse(List<Customer> customers) {
        List<CustomerResponse> response = new ArrayList<>();
        for (Customer customer: customers) {
            response.add(toResponse(customer));
        }
        return response;
    }

    public Customer toEntity(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setEmail(customerRequest.getEmail());
        customer.setPassword(customerRequest.getPassword());
        customer.setImageUrl(customerRequest.getImageUrl());
        customer.setPhone(customerRequest.getPhone());
        customer.setRole(customerRequest.getRole());
        customer.setStatus(customerRequest.getStatus());
        return customer;
    }
}
