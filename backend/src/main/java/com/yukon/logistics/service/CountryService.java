package com.yukon.logistics.service;

import com.yukon.logistics.persistence.entity.Country;

import java.util.List;

public interface CountryService {
    Country addCountry(Country country);
    List<Country> findAllCountries();
    Country updateCountry(Country country);
    Country findCountryById(Long id);
    Country findCountryByName(String name);
    void deleteCountryById(Long id);
}
