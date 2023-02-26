package com.yukon.logistics.model.mapper;

import com.yukon.logistics.model.dto.TruckerRequest;
import com.yukon.logistics.model.dto.TruckerResponse;
import com.yukon.logistics.persistence.entity.Trucker;

import java.util.ArrayList;
import java.util.List;

public class TruckerMapper {
    public TruckerResponse toResponse(Trucker trucker, boolean includeOrders) {
        TruckerResponse response = TruckerResponse.builder()
                .id(trucker.getId())
                .firstName(trucker.getFirstName())
                .lastName(trucker.getLastName())
                .email(trucker.getEmail())
                .imageUrl(trucker.getImageUrl())
                .phone(trucker.getPhone())
                .role(trucker.getRole())
                .status(trucker.getStatus())
                .build();

        if(trucker.getOrders() != null && includeOrders) {
            response.setOrders(new OrderMapper().toListResponse(trucker.getOrders(), false, true));
        }

        return response;
    }

    public List<TruckerResponse> toListResponse(List<Trucker> truckers, boolean includeOrders) {
        List<TruckerResponse> response = new ArrayList<>();
        for (Trucker trucker: truckers) {
            response.add(toResponse(trucker, includeOrders));
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
