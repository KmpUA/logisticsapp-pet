package com.yukon.logistics.model.mapper;

import com.yukon.logistics.model.dto.DispatcherRequest;
import com.yukon.logistics.model.dto.DispatcherResponse;
import com.yukon.logistics.model.dto.TruckerResponse;
import com.yukon.logistics.persistence.entity.Dispatcher;
import com.yukon.logistics.persistence.entity.Trucker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DispatcherMapper {
    public DispatcherResponse toResponse(Dispatcher dispatcher, boolean includeTruckers) {
                DispatcherResponse response = DispatcherResponse.builder()
                .id(dispatcher.getId())
                .firstName(dispatcher.getFirstName())
                .lastName(dispatcher.getLastName())
                .email(dispatcher.getEmail())
                .imageUrl(dispatcher.getImageUrl())
                .phone(dispatcher.getPhone())
                .role(dispatcher.getRole())
                .status(dispatcher.getStatus())
                .build();

        if(includeTruckers) {
            List<TruckerResponse> truckers = new ArrayList<>();
            if(dispatcher.getTruckers() != null) {
                for (Trucker trucker : dispatcher.getTruckers()) {
                    truckers.add(new TruckerMapper().toResponse(trucker, false, false));
                }
            }
            response.setTruckers(truckers);
        }

        return response;
    }

    public List<DispatcherResponse> toListResponse(List<Dispatcher> dispatchers, boolean includeTruckers) {
        List<DispatcherResponse> response = new ArrayList<>();
        for(Dispatcher dispatcher : dispatchers) {
            response.add(toResponse(dispatcher, includeTruckers));
        }
        return response;
    }

    public Dispatcher toEntity (DispatcherRequest dispatcherRequest) {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setFirstName(dispatcherRequest.getFirstName());
        dispatcher.setLastName(dispatcherRequest.getLastName());
        dispatcher.setEmail(dispatcherRequest.getEmail());
        dispatcher.setPassword(dispatcherRequest.getPassword());
        dispatcher.setImageUrl(dispatcherRequest.getImageUrl());
        dispatcher.setPhone(dispatcherRequest.getPhone());
        dispatcher.setRole(dispatcherRequest.getRole());
        dispatcher.setStatus(dispatcherRequest.getStatus());
        return dispatcher;
    }
}
