package com.yukon.logistics.model.mapper;

import com.yukon.logistics.model.dto.OrderRequest;
import com.yukon.logistics.model.dto.OrderResponse;
import com.yukon.logistics.persistence.entity.City;
import com.yukon.logistics.persistence.entity.Customer;
import com.yukon.logistics.persistence.entity.Order;
import com.yukon.logistics.persistence.entity.Trucker;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public OrderResponse toResponse(Order order, boolean includeTrucker, boolean includeCustomer) {
        OrderResponse response = OrderResponse.builder()
                .id(order.getId())
                .cityFrom(order.getFrom().getId())
                .cityTo(order.getTo().getId())
                .cargoDescription(order.getCargoDescription())
                .cargoWeight(order.getCargoWeight())
                .created(order.getCreated())
                .modify(order.getModify())
                .startDeliver(order.getStartDeliver())
                .endDeliver(order.getEndDeliver())
                .completed(order.getCompleted())
                .build();
        if(order.getTrucker() != null && includeTrucker) {
            response.setTrucker(new TruckerMapper().toResponse(order.getTrucker(), false));
        }
        if(includeCustomer) {
            response.setCustomer(new CustomerMapper().toResponse(order.getCustomer(), false));
        }
        return response;
    }

    public List<OrderResponse> toListResponse(List<Order> orders, boolean includeTrucker, boolean includeCustomer) {
        List<OrderResponse> response = new ArrayList<>();
        for (Order order: orders) {
            response.add(toResponse(order, includeTrucker, includeCustomer));
        }
        return response;
    }

    public Order toEntity(OrderRequest orderRequest, City from, City to, Trucker trucker, Customer customer) {
        Order order = new Order();
        order.setFrom(from);
        order.setTo(to);
        order.setCustomer(customer);
        order.setCargoDescription(orderRequest.getCargoDescription());
        order.setCargoWeight(orderRequest.getCargoWeight());
        order.setTrucker(trucker);
        order.setStartDeliver(orderRequest.getStartDeliver());
        order.setEndDeliver(orderRequest.getEndDeliver());
        order.setCompleted(orderRequest.getCompleted());
        return order;
    }
}
