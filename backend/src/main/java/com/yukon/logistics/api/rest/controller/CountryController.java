package com.yukon.logistics.api.rest.controller;

import com.yukon.logistics.model.dto.CountryRequest;
import com.yukon.logistics.model.dto.CountryResponse;
import com.yukon.logistics.model.mapper.CountryMapper;
import com.yukon.logistics.persistence.entity.Country;
import com.yukon.logistics.service.impl.CountryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import static java.lang.Long.parseLong;

@RestController
@RequestMapping("/countries")
@AllArgsConstructor
public class CountryController {
    private final CountryServiceImpl countryService;

    @GetMapping("/all")
    public ResponseEntity<List<CountryResponse>> getAll() {
        List<CountryResponse> countryResponseList = new CountryMapper()
                .toListResponse(countryService.findAllCountries());
        return new ResponseEntity<>(countryResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryResponse> getById(@PathVariable("id") String id) {
        CountryResponse countryResponse = new CountryMapper()
                .toResponse(countryService.findCountryById(parseLong(id)));
        return new ResponseEntity<>(countryResponse, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CountryResponse> getByName(@PathVariable("name") String name) {
        CountryResponse countryResponse = new CountryMapper()
                .toResponse(countryService.findCountryByName(name));
        return new ResponseEntity<>(countryResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CountryResponse> add(@RequestBody CountryRequest countryRequest) {
        Country country = new CountryMapper().toEntity(countryRequest);
        CountryResponse countryResponse = new CountryMapper().toResponse(countryService.addCountry(country));
        return new ResponseEntity<>(countryResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        countryService.deleteCountryById(parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryResponse> update(@PathVariable("id") String id,
                                                  @RequestBody CountryRequest countryRequest) {
        Country country = new CountryMapper().toEntity(countryRequest);
        country.setId(parseLong(id));
        CountryResponse countryResponse = new CountryMapper().toResponse(countryService.updateCountry(country));
        return  new ResponseEntity<>(countryResponse, HttpStatus.OK);
    }
}
