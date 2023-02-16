package com.yukon.logistics.service.impl;

import com.yukon.logistics.persistence.entity.City;
import com.yukon.logistics.persistence.repository.CityRepository;
import com.yukon.logistics.service.CityService;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        super();
        this.cityRepository = cityRepository;
    }

    @Override
    public City addCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public List<City> findAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City updateCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City findCityById(Long id) {
        return cityRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    @Override
    public City findCityByName(String name) {
        return cityRepository.findByName(name).orElseThrow(EntityExistsException::new);
    }

    @Override
    public void deleteCityById(Long id) {
        if(cityRepository.findById(id).isEmpty()){
            throw new EntityExistsException();
        }
        else{
            cityRepository.deleteById(id);
        }
    }
}
