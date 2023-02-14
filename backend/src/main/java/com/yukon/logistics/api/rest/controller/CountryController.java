package com.yukon.logistics.api.rest.controller;

import com.yukon.logistics.model.dto.CountryResponse;
import com.yukon.logistics.model.mapper.CountryMapper;
import com.yukon.logistics.service.impl.CountryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
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
    public ResponseEntity<CountryResponse> getById(@PathVariable("id") long id) {
        CountryResponse countryResponse = new CountryMapper().toResponse(countryService.findCountryById(id));
        return new ResponseEntity<>(countryResponse, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CountryResponse> getByName(@PathVariable("name") String name) {
        CountryResponse countryResponse = new CountryMapper().toResponse(countryService.findCountryByName(name));
        return new ResponseEntity<>(countryResponse, HttpStatus.OK);
    }
}
