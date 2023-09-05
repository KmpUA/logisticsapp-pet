package com.yukon.logistics.service;

import com.yukon.logistics.persistence.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer);
    List<Customer> findAllCustomers();
    Customer updateCustomer(Customer customer);
    Customer findCustomerByOrder(Long id);
    Customer findCustomerById(Long id);
    void deleteCustomerById(Long id);
}
