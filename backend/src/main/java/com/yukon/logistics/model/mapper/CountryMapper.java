package com.yukon.logistics.model.mapper;

import com.yukon.logistics.model.dto.CountryRequest;
import com.yukon.logistics.model.dto.CountryResponse;
import com.yukon.logistics.persistence.entity.City;
import com.yukon.logistics.persistence.entity.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryMapper {
    public CountryResponse toResponse(Country country) {
        List<String> cityNames = new ArrayList<>();
        for (City city : country.getCities()) {
            cityNames.add(city.getName());
        }
        return CountryResponse.builder().id(country.getId())
                .countryName(country.getName()).cityNames(cityNames).build();
    }

    public List<CountryResponse> toListResponse(List<Country> countries) {
        List<CountryResponse> response = new ArrayList<>();
        for (Country country : countries) {
            response.add(toResponse(country));
        }
        return response;
    }

    public Country toEntity(CountryRequest countryResponse) {
        Country country = new Country();
        //country.setId(countryResponse.getId());
        country.setName(countryResponse.getCountryName());
        return country;
    }
}
