package com.yukon.logistics.api.rest.controller;

import com.yukon.logistics.model.dto.TruckerRequest;
import com.yukon.logistics.model.dto.TruckerResponse;
import com.yukon.logistics.model.mapper.TruckerMapper;
import com.yukon.logistics.persistence.entity.Trucker;
import com.yukon.logistics.service.TruckerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Long.parseLong;

@Controller
@RequestMapping("/truckers")
public class TruckerController {
    private final TruckerService truckerService;

    public TruckerController(TruckerService truckerService) {
        super();
        this.truckerService = truckerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TruckerResponse>> getAll() {
        List<TruckerResponse> truckerResponseList = new TruckerMapper()
                .toListResponse(truckerService.findAllTruckers());
        return new ResponseEntity<>(truckerResponseList, HttpStatus.OK);
    }

    @GetMapping("/truck/{truck_id}")
    public ResponseEntity<TruckerResponse> getByTruck(@PathVariable("truck_id") String id) {
        TruckerResponse truckerResponse = new TruckerMapper()
                .toResponse(truckerService.findTruckerByTruck(parseLong(id)));
        return new ResponseEntity<>(truckerResponse, HttpStatus.OK);
    }

    @GetMapping("/dispatcher/{dispatcher_id}")
    public ResponseEntity<List<TruckerResponse>> getByDispatcher(@PathVariable("dispatcher_id") String id) {
        List<TruckerResponse> truckerResponse = new TruckerMapper()
                .toListResponse(truckerService.findTruckerByDispatcher(parseLong(id)));
        return new ResponseEntity<>(truckerResponse, HttpStatus.OK);
    }

    @GetMapping("/order/{order_id}")
    public ResponseEntity<TruckerResponse> getByOrder(@PathVariable("order_id") String id) {
        TruckerResponse truckerResponse = new TruckerMapper()
                .toResponse(truckerService.findTruckerByOrder(parseLong(id)));
        return new ResponseEntity<>(truckerResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TruckerResponse> addTrucker(@RequestBody TruckerRequest truckerRequest) {
        Trucker trucker = new TruckerMapper().toEntity(truckerRequest);
        TruckerResponse truckerResponse = new TruckerMapper()
                .toResponse(truckerService.addTrucker(trucker));
        return new ResponseEntity<>(truckerResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TruckerResponse> updateTrucker(@PathVariable("id") String id,
                                                         @RequestBody TruckerRequest truckerRequest) {
        Trucker trucker = new TruckerMapper().toEntity(truckerRequest);
        trucker.setId(parseLong(id));
        TruckerResponse truckerResponse= new TruckerMapper()
                .toResponse(truckerService.updateTrucker(trucker));
        return new ResponseEntity<>(truckerResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrucker(@PathVariable String id) {
        truckerService.deleteCityById(parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
