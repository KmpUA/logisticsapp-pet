package com.yukon.logistics.api.rest.controller;

import com.yukon.logistics.model.dto.TruckRequest;
import com.yukon.logistics.model.dto.TruckResponse;
import com.yukon.logistics.model.mapper.TruckMapper;
import com.yukon.logistics.persistence.entity.Truck;
import com.yukon.logistics.service.TruckService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import static java.lang.Long.parseLong;

@Controller
@AllArgsConstructor
@RequestMapping("/trucks")
public class TruckController {
    private final TruckService truckService;

    @GetMapping("/all")
    public ResponseEntity<List<TruckResponse>> getAll() {
        List<TruckResponse> truckResponseList = new
                TruckMapper().toListResponse(truckService.findAllTrucks());
        return new ResponseEntity<>(truckResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TruckResponse> getById(@PathVariable("id") String id) {
        TruckResponse truckResponse = new
                TruckMapper().toResponse(truckService.findTruckById(parseLong(id)));
        return new ResponseEntity<>(truckResponse, HttpStatus.OK);
    }

    @GetMapping("/licensePlate/{licensePlate}")
    public ResponseEntity<TruckResponse> getByLicensePlate(@PathVariable("licensePlate") String licensePlate) {
        TruckResponse truckResponse = new
                TruckMapper().toResponse(truckService.findByLicensePlate(licensePlate));
        return new ResponseEntity<>(truckResponse, HttpStatus.OK);
    }

    @GetMapping("/vinCode/{vinCode}")
    public ResponseEntity<TruckResponse> getByVinCode(@PathVariable("vinCode") String vinCode) {
        TruckResponse truckResponse = new
                TruckMapper().toResponse(truckService.findTruckByVinCode(vinCode));
        return new ResponseEntity<>(truckResponse, HttpStatus.OK);
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<List<TruckResponse>> getAllByModel(@PathVariable("model") String model) {
        List<TruckResponse> truckResponseList = new
                TruckMapper().toListResponse(truckService.findAllTrucksByModel(model));
        return new ResponseEntity<>(truckResponseList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        truckService.deleteTruckById(parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TruckResponse> addTruck(@RequestBody TruckRequest truckRequest) {
        Truck truck = new TruckMapper().toEntity(truckRequest);
        TruckResponse truckResponse = new TruckMapper()
                .toResponse(truckService.addTruck(truck));
        return new ResponseEntity<>(truckResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TruckResponse> updateTruck(@PathVariable("id") String id,
                                                     @RequestBody TruckRequest truckRequest) {
        Truck truck = new TruckMapper().toEntity(truckRequest);
        truck.setId(parseLong(id));
        TruckResponse truckResponse = new TruckMapper()
                .toResponse(truckService.updateTruck(truck));
        return new ResponseEntity<>(truckResponse, HttpStatus.CREATED);
    }
}
