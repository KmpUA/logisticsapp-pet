package com.yukon.logistics.service;

import com.yukon.logistics.persistence.entity.City;
import com.yukon.logistics.persistence.entity.Country;
import com.yukon.logistics.persistence.repository.CityRepository;
import com.yukon.logistics.service.impl.CityServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {
    @Mock
    private static CityRepository cityRepository;
    private static CityServiceImpl cityService;
    private static City city1;
    private static City city2;

    @BeforeEach
    void init() {
        Country country = new Country(1L, "country_1", null);
        city1 = new City(1L, "city_1", country);
        city2 = new City(2L, "city_2", country);

        cityService = new CityServiceImpl(cityRepository);
    }

    @Test
    void addCityTest() {
        City expected = city1;
        when(cityRepository.save(expected)).thenReturn(expected);

        City actual = cityService.addCity(expected);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findAllCitiesTest() {
        List<City> expected = new ArrayList<>();
        expected.add(city1);
        expected.add(city2);

        when(cityRepository.findAll()).thenReturn(expected);

        List<City> actual = cityService.findAllCities();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findCityByIdTest() {
        City expected = city1;
        when(cityRepository.findById(expected.getId())).thenReturn(Optional.of(expected));

        City actual = cityService.findCityById(expected.getId());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findCityByLicenceNameTest() {
        City expected = city2;
        when(cityRepository.findByName(expected.getName())).thenReturn(Optional.of(expected));

        City actual = cityService.findCityByName(expected.getName());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteCityByIdTest() {
        when(cityRepository.findById(city1.getId())).thenReturn(Optional.of(city1));
        doNothing().when(cityRepository).deleteById(city1.getId());

        cityService.deleteCityById(city1.getId());
        verify(cityRepository, times(1)).deleteById(city1.getId());
    }
}
