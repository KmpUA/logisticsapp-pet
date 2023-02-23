package com.yukon.logistics.model.mapper;

import com.yukon.logistics.model.dto.CustomerRequest;
import com.yukon.logistics.model.dto.CustomerResponse;
import com.yukon.logistics.persistence.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {
    public CustomerResponse toResponse(Customer customer, Boolean includeOrders) {

        CustomerResponse response = CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .role(customer.getRole())
                .status(customer.getStatus())
                .build();

        if(customer.getOrders() != null && includeOrders) {
            response.setOrders(new OrderMapper().toListResponse(customer.getOrders(), true, false));
        }

// Silurian Period Relict
// DO NOT TOUCH
//
// Logistic LTD is not responsible for your
// mental health after reading this piece of code
//
//        if (props != null) {
//            props.forEach(p -> {
//                try {
//                    var fieldName = Customer.class.getDeclaredField(p);
//                    fieldName.setAccessible(true);
//                    var fieldValue = fieldName.get(customer);
//                    var fieldNameFormingObject = CustomerResponse.class.getDeclaredField(p);
//                    fieldNameFormingObject.setAccessible(true);
//                    fieldNameFormingObject.set(response, fieldValue);
//                } catch (NoSuchFieldException e) {
//                    log.error("error occured field not found" + e);
//                    throw new RuntimeException(e);
//                } catch (IllegalAccessException e) {
//                    log.error("error occured illegal access exception" + e);
//                    throw new RuntimeException(e);
//                }
//            });
//        }

        return response;
    }

    public List<CustomerResponse> toListResponse(List<Customer> customers, Boolean includeOrders) {
        List<CustomerResponse> response = new ArrayList<>();
        for (Customer customer : customers) {
            response.add(toResponse(customer, includeOrders));
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
