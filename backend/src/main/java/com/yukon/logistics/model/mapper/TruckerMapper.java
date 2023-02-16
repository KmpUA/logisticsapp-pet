package com.yukon.logistics.model.mapper;

import com.yukon.logistics.model.dto.TruckerRequest;
import com.yukon.logistics.model.dto.TruckerResponse;
import com.yukon.logistics.persistence.entity.Order;
import com.yukon.logistics.persistence.entity.Trucker;

import java.util.ArrayList;
import java.util.List;

public class TruckerMapper {
    public TruckerResponse toResponse(Trucker trucker) {
        List<String> orders = new ArrayList<>();
        for(Order order: trucker.getOrders()){
            orders.add(order.getId().toString());
        }
        new TruckerResponse();
        return TruckerResponse.builder().
                truckName(trucker.getTruck().getModel()).
                dispatcherName(trucker.getDispatcher().getFirstName()
                        + trucker.getDispatcher().getLastName()).
                orders(orders).build();
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
        trucker.setDispatcher(truckerRequest.getDispatcher());
        trucker.setTruck(truckerRequest.getTruck());
        return trucker;
    }
}
