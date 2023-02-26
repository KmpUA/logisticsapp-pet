package com.yukon.logistics.api.rest.controller;

import com.yukon.logistics.model.dto.TruckerRequest;
import com.yukon.logistics.model.dto.TruckerResponse;
import com.yukon.logistics.model.mapper.TruckerMapper;
import com.yukon.logistics.persistence.entity.Dispatcher;
import com.yukon.logistics.persistence.entity.Trucker;
import com.yukon.logistics.service.DispatcherService;
import com.yukon.logistics.service.TruckerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Long.parseLong;

@Controller
@AllArgsConstructor
@RequestMapping("/truckers")
public class TruckerController {
    private final TruckerService truckerService;
    private final DispatcherService dispatcherService;

    @GetMapping("/all")
    public ResponseEntity<List<TruckerResponse>> getAll(boolean includeOrders) {
        List<TruckerResponse> truckerResponseList = new TruckerMapper()
                .toListResponse(truckerService.findAllTruckers(), includeOrders);
        return new ResponseEntity<>(truckerResponseList, HttpStatus.OK);
    }

    @GetMapping("/truck/{truck_id}")
    public ResponseEntity<TruckerResponse> getByTruck(@PathVariable("truck_id") String id) {
        TruckerResponse truckerResponse = new TruckerMapper()
                .toResponse(truckerService.findTruckerByTruck(parseLong(id)), true, true);
        return new ResponseEntity<>(truckerResponse, HttpStatus.OK);
    }

    @GetMapping("/dispatcher/{dispatcher_id}")
    public ResponseEntity<List<TruckerResponse>> getByDispatcher(@PathVariable("dispatcher_id") String id) {
        List<TruckerResponse> truckerResponse = new TruckerMapper()
                .toListResponse(truckerService.findTruckerByDispatcher(parseLong(id)), false);
        return new ResponseEntity<>(truckerResponse, HttpStatus.OK);
    }

    @GetMapping("/order/{order_id}")
    public ResponseEntity<TruckerResponse> getByOrder(@PathVariable("order_id") String id) {
        TruckerResponse truckerResponse = new TruckerMapper()
                .toResponse(truckerService.findTruckerByOrder(parseLong(id)), false, true);
        return new ResponseEntity<>(truckerResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TruckerResponse> addTrucker(@RequestBody TruckerRequest truckerRequest) {
        Trucker trucker = new TruckerMapper().toEntity(truckerRequest, null);
        TruckerResponse truckerResponse = new TruckerMapper()
                .toResponse(truckerService.addTrucker(trucker), false, false);
        return new ResponseEntity<>(truckerResponse, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TruckerResponse> updateTrucker(@PathVariable("id") String id,
                                                         @RequestBody TruckerRequest truckerRequest) {
        Dispatcher dispatcher = null;
        if(truckerRequest.getDispatcher() != null) {
            dispatcher = dispatcherService.findDispatcherById(truckerRequest.getDispatcher());
        }
        Trucker trucker = new TruckerMapper().toEntity(truckerRequest, dispatcher);
        trucker.setId(parseLong(id));

        if(truckerRequest.getPassword() == null) {
            trucker.setPassword(truckerService.findTruckerById(parseLong(id)).getPassword());
        }

        TruckerResponse truckerResponse= new TruckerMapper()
                .toResponse(truckerService.updateTrucker(trucker), false, true);
        return new ResponseEntity<>(truckerResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrucker(@PathVariable String id) {
        truckerService.deleteCityById(parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
