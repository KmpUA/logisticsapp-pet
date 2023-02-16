package com.yukon.logistics.model.mapper;

import com.yukon.logistics.model.dto.TruckerRequest;
import com.yukon.logistics.model.dto.TruckerResponse;
import com.yukon.logistics.persistence.entity.Order;
import com.yukon.logistics.persistence.entity.Trucker;

import java.util.ArrayList;
import java.util.List;

public class TruckerMapper {
    public TruckerResponse toResponse(Trucker trucker) {
        List<Long> orders = new ArrayList<>();
        if(trucker.getOrders() != null) {
            for (Order order : trucker.getOrders()) {
                orders.add(order.getId());
            }
        }
        return TruckerResponse.builder().ordersId(orders)
                .id(trucker.getId())
                .firstName(trucker.getFirstName())
                .lastName(trucker.getLastName())
                .email(trucker.getEmail())
                .imageUrl(trucker.getImageUrl())
                .phone(trucker.getPhone())
                .role(trucker.getRole())
                .status(trucker.getStatus())
                .build();
    }

    public List<TruckerResponse> toListResponse(List<Trucker> truckers) {
        List<TruckerResponse> response = new ArrayList<>();
        for (Trucker trucker: truckers) {
            response.add(toResponse(trucker));
        }
        return response;
    }

    public Trucker toEntity(TruckerRequest truckerRequest) {
        Trucker trucker = new Trucker();
        trucker.setFirstName(truckerRequest.getFirstName());
        trucker.setLastName(truckerRequest.getLastName());
        trucker.setEmail(truckerRequest.getEmail());
        trucker.setPassword(truckerRequest.getPassword());
        trucker.setImageUrl(truckerRequest.getImageUrl());
        trucker.setPhone(truckerRequest.getPhone());
        trucker.setRole(truckerRequest.getRole());
        trucker.setStatus(truckerRequest.getStatus());
        return trucker;
    }
}
