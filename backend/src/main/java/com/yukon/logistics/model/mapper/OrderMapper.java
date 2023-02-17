package com.yukon.logistics.model.mapper;

import com.yukon.logistics.model.dto.OrderRequest;
import com.yukon.logistics.model.dto.OrderResponse;
import com.yukon.logistics.persistence.entity.City;
import com.yukon.logistics.persistence.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public OrderResponse toResponse(Order order) {
        new OrderResponse();
        return OrderResponse.builder()
                .id(order.getId())
                .cityFrom(order.getFrom().getId())
                .cityTo(order.getTo().getId())
                .cargoDescription(order.getCargoDescription())
                .cargoWeight(order.getCargoWeight())
                .created(order.getCreated())
                .modify(order.getModify())
                .startDeliver(order.getStartDeliver())
                .endDeliver(order.getEndDeliver())
                .build();
    }

    public List<OrderResponse> toListResponse(List<Order> orders) {
        List<OrderResponse> response = new ArrayList<>();
        for (Order order: orders) {
            response.add(toResponse(order));
        }
        return response;
    }

    public Order toEntity(OrderRequest orderRequest, City from, City to) {
        Order order = new Order();
        order.setFrom(from);
        order.setTo(to);
        order.setCargoDescription(orderRequest.getCargoDescription());
        order.setCargoWeight(orderRequest.getCargoWeight());
        return order;
    }
}
