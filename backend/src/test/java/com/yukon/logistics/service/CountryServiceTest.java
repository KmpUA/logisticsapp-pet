package com.yukon.logistics.service;

import com.yukon.logistics.persistence.entity.Country;
import com.yukon.logistics.persistence.repository.CountryRepository;
import com.yukon.logistics.service.impl.CountryServiceImpl;
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
public class CountryServiceTest {
    @Mock
    private static CountryRepository countryRepository;
    private static CountryServiceImpl countryService;
    private static Country country1;
    private static Country country2;

    @BeforeEach
    void init() {
        country1 = new Country(1L, "country_1", null);
        country2 = new Country(2L, "country_2", null);

        countryService = new CountryServiceImpl(countryRepository);
    }

    @Test
    void addCountryTest() {
        Country expected = country1;
        when(countryRepository.save(expected)).thenReturn(expected);

        Country actual = countryService.addCountry(expected);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findAllCitiesTest() {
        List<Country> expected = new ArrayList<>();
        expected.add(country1);
        expected.add(country2);

        when(countryRepository.findAll()).thenReturn(expected);

        List<Country> actual = countryService.findAllCountries();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findCountryByIdTest() {
        Country expected = country1;
        when(countryRepository.findById(expected.getId())).thenReturn(Optional.of(expected));

        Country actual = countryService.findCountryById(expected.getId());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findCountryByLicenceNameTest() {
        Country expected = country2;
        when(countryRepository.findByName(expected.getName())).thenReturn(Optional.of(expected));

        Country actual = countryService.findCountryByName(expected.getName());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteCountryByIdTest() {
        doNothing().when(countryRepository).deleteById(country1.getId());

        countryService.deleteCountryById(country1.getId());
        verify(countryRepository, times(1)).deleteById(country1.getId());
    }
}
