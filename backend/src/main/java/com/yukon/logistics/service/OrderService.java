package com.yukon.logistics.service;

import com.yukon.logistics.persistence.entity.Order;

import java.util.List;

public interface OrderService {
    Order addOrder(Order order);
    List<Order> findAllOrders();
    Order updateOrder(Order order);
    Order findOrderById(Long id);
    Order findOrderByCityFrom(String city);
    Order findOrderByCityTo(String city);
    List<Order> findOrdersByTruckerId(Long truckerId);
    void deleteOrderById(Long id);
}
