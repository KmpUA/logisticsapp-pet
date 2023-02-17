package com.yukon.logistics.service;

import com.yukon.logistics.persistence.entity.City;

import java.util.List;

public interface CityService {
    City addCity(City city);
    List<City> findAllCities();
    City updateCity(City city);
    City findCityById(Long id);
    City findCityByName(String name);
    void deleteCityById(Long id);
}
