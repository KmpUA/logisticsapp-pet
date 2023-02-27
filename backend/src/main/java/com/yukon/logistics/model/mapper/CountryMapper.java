package com.yukon.logistics.model.mapper;

import com.yukon.logistics.model.dto.CityResponse;
import com.yukon.logistics.model.dto.CountryRequest;
import com.yukon.logistics.model.dto.CountryResponse;
import com.yukon.logistics.persistence.entity.City;
import com.yukon.logistics.persistence.entity.Country;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CountryMapper {
    public CountryResponse toResponse(Country country) {
        List<CityResponse> cities = new ArrayList<>();
        if(country.getCities() != null) {
            for (City city : country.getCities()) {
                cities.add(new CityMapper().toResponse(city));
            }
        }
        return CountryResponse.builder().id(country.getId())
                .countryName(country.getName()).cities(cities).build();
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
        country.setName(countryResponse.getCountryName());
        return country;
    }
}
