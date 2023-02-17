package com.yukon.logistics.api.rest.controller;

import com.yukon.logistics.model.dto.CityRequest;
import com.yukon.logistics.model.dto.CityResponse;
import com.yukon.logistics.model.mapper.CityMapper;
import com.yukon.logistics.persistence.entity.City;
import com.yukon.logistics.service.CityService;
import com.yukon.logistics.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Long.parseLong;

@Controller
@AllArgsConstructor
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;
    private final CountryService countryService;

    @GetMapping("/all")
    public ResponseEntity<List<CityResponse>> getAll() {
        List<CityResponse> cityResponseList = new CityMapper().toListResponse(cityService.findAllCities());
        return new ResponseEntity<>(cityResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getById(@PathVariable("id") String id) {
        CityResponse cityResponse = new CityMapper().toResponse(cityService.findCityById(parseLong(id)));
        return new ResponseEntity<>(cityResponse, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CityResponse> getByName(@PathVariable("name") String name) {
        CityResponse cityResponse = new CityMapper().toResponse(cityService.findCityByName(name));
        return new ResponseEntity<>(cityResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CityResponse> addCity(@RequestBody CityRequest cityRequest){
        City city = new CityMapper().toEntity(cityRequest,
                countryService.findCountryByName(cityRequest.getCountryName()));
        CityResponse cityResponse = new CityMapper().toResponse(cityService.addCity(city));
        return new ResponseEntity<>(cityResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> updateCity(@PathVariable("id") String id,
                                                   @RequestBody CityRequest cityRequest){
        City city = new CityMapper().toEntity(cityRequest,
                countryService.findCountryByName(cityRequest.getCountryName()));
        city.setId(parseLong(id));
        CityResponse cityResponse = new CityMapper().toResponse(cityService.updateCity(city));
        return new ResponseEntity<>(cityResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable String id){
        cityService.deleteCityById(parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
