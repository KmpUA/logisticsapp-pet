package com.yukon.logistics.model.mapper;

import com.yukon.logistics.model.dto.OrderRequest;
import com.yukon.logistics.model.dto.OrderResponse;
import com.yukon.logistics.persistence.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public OrderResponse toResponse(Order order) {
        new OrderResponse();
        return OrderResponse.builder().id(order.getId())
                .cityFrom(order.getFrom().getName())
                .cityTo(order.getTo().getName()).
                truckerName(order.getTrucker().
                        getFirstName() + " " + order.getTrucker().
                        getLastName()).
                cargoDescription(order.getCargoDescription()).
                cargoWeight(order.getCargoWeight()).build();
    }

    public List<OrderResponse> toListResponse(List<Order> orders) {
        List<OrderResponse> response = new ArrayList<>();
        for (Order order: orders) {
            response.add(toResponse(order));
        }
        return response;
    }

    public Order toEntity(OrderRequest orderRequest) {
        Order order = new Order();
        order.setId(orderRequest.getId());
        order.setTo(orderRequest.getCityTo());
        order.setFrom(orderRequest.getCityFrom());
        order.setTrucker(orderRequest.getTrucker());
        return order;
    }
}
