package com.yukon.logistics.model.mapper;

import com.yukon.logistics.model.dto.CityRequest;
import com.yukon.logistics.model.dto.CityResponse;
import com.yukon.logistics.persistence.entity.City;

import java.util.ArrayList;
import java.util.List;

public class CityMapper {

    public CityResponse toResponse(City city) {
        new CityResponse();
        return CityResponse.builder().id(city.getId())
                .cityName(city.getName()).countyName(city.getCountry().getName()).build();
    }

    public List<CityResponse> toListResponse(List<City> cities) {
        List<CityResponse> response = new ArrayList<>();
        for (City city: cities) {
            response.add(toResponse(city));
        }
        return response;
    }

    public City toEntity(CityRequest cityRequest) {
        City city = new City();
        city.setId(cityRequest.getId());
        city.setName(cityRequest.getCityName());
        return city;
    }
}
