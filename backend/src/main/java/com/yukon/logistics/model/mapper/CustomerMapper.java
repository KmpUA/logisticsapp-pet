package com.yukon.logistics.model.mapper;

import com.yukon.logistics.model.dto.CustomerRequest;
import com.yukon.logistics.model.dto.CustomerResponse;
import com.yukon.logistics.persistence.entity.Customer;
import com.yukon.logistics.persistence.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {
    public CustomerResponse toResponse(Customer customer) {
        List<String> orders = new ArrayList<>();
        for(Order order: customer.getOrders()){
            orders.add(order.getId().toString());
        }
        new CustomerResponse();
        return CustomerResponse.builder().ordersId(orders).build();
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
        customer.setOrders(customerRequest.getOrders());
        return customer;
    }
}
