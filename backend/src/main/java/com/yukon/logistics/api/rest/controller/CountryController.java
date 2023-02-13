package com.yukon.logistics.api.rest.controller;

import com.yukon.logistics.persistence.entity.Country;
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
    public ResponseEntity<List<Country>> getAll() {
        List<Country> countries = countryService.findAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getById(@PathVariable("id") long id) {
        Country country = countryService.findCountryById(id);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Country> getByName(@PathVariable("name") String name) {
        Country country = countryService.findCountryByName(name);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }
}
