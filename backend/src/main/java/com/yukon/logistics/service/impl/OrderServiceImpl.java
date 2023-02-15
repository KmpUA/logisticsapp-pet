package com.yukon.logistics.service.impl;

import com.yukon.logistics.persistence.entity.Order;
import com.yukon.logistics.persistence.repository.OrderRepository;
import com.yukon.logistics.service.OrderService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        super();
        this.orderRepository = orderRepository;
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    @Override
    public Order findOrderByCityFrom(String city) {
        return orderRepository.findByFrom(city).orElseThrow(EntityExistsException::new);
    }

    @Override
    public Order findOrderByCityTo(String city) {
        return orderRepository.findByTo(city).orElseThrow(EntityExistsException::new);
    }

    @Override
    public Order findOrderByTrucker(String truckerName) {
        return orderRepository.findByTrucker(truckerName).orElseThrow(EntityExistsException::new);
    }

    @Override
    public void deleteOrderById(Long id) {
        if(orderRepository.findById(id).isPresent()){
            orderRepository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException(id + "Order not found");
        }
    }
}
