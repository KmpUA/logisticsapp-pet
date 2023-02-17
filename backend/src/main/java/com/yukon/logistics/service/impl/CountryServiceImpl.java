package com.yukon.logistics.service.impl;

import com.yukon.logistics.persistence.entity.Country;
import com.yukon.logistics.persistence.repository.CountryRepository;
import com.yukon.logistics.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    public final CountryRepository countryRepository;

    @Override
    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country updateCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country findCountryById(Long id) {
        return countryRepository.findById(id).orElseThrow();
    }

    @Override
    public Country findCountryByName(String name) {
        return countryRepository.findByName(name).orElseThrow();
    }

    @Override
    public void deleteCountryById(Long id) {
        countryRepository.deleteById(id);
    }
}
