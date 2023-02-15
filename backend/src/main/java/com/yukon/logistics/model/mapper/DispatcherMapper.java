package com.yukon.logistics.model.mapper;

import com.yukon.logistics.model.dto.DispatcherRequest;
import com.yukon.logistics.model.dto.DispatcherResponse;
import com.yukon.logistics.persistence.entity.Dispatcher;
import com.yukon.logistics.persistence.entity.Trucker;

import java.util.ArrayList;
import java.util.List;

public class DispatcherMapper {
    public DispatcherResponse toResponse(Dispatcher dispatcher) {
        List<Long> truckers = new ArrayList<>();
        for(Trucker trucker : dispatcher.getTruckers()) {
            truckers.add(trucker.getId());
        }
        return DispatcherResponse.builder()
                .truckersId(truckers).build();
    }

    public List<DispatcherResponse> toListResponse(List<Dispatcher> dispatchers) {
        List<DispatcherResponse> response = new ArrayList<>();
        for(Dispatcher dispatcher : dispatchers) {
            response.add(toResponse(dispatcher));
        }
        return response;
    }

    public Dispatcher toEntity (DispatcherRequest dispatcherRequest) {
        Dispatcher dispatcher = new Dispatcher();
        List<Trucker> truckers = new ArrayList<>();
        truckers.add(dispatcherRequest.getTrucker());
        dispatcher.setTruckers(truckers);
        return dispatcher;
    }
}
