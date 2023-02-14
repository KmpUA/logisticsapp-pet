package com.yukon.logistics.api.rest.controller;

import com.yukon.logistics.model.dto.CityResponse;
import com.yukon.logistics.model.mapper.CityMapper;
import com.yukon.logistics.repository.CityRepository;
import com.yukon.logistics.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;
    private final CityRepository cityRepository;

    @GetMapping("/all")
    public ResponseEntity<List<CityResponse>> getAll() {
        List<CityResponse> cityResponseList = new CityMapper().toListResponse(cityService.findAllCities());
        return new ResponseEntity<>(cityResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getById(@PathVariable("id") Long id) {
        CityResponse cityResponse = new CityMapper().toResponse(cityService.findCityById(id));
        return new ResponseEntity<>(cityResponse, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CityResponse> getByName(@PathVariable("name") String name) {
        CityResponse cityResponse = new CityMapper().toResponse(cityService.findCityByName(name));
        return new ResponseEntity<>(cityResponse, HttpStatus.OK);
    }
}
