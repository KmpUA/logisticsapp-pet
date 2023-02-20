package com.yukon.logistics;

import com.yukon.logistics.persistence.entity.City;
import com.yukon.logistics.persistence.entity.Country;
import com.yukon.logistics.persistence.repository.CountryRepository;
import com.yukon.logistics.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ActiveProfiles("test")
public class CountryServiceTest {
    @Mock
    private CountryRepository countryRepository;
    @InjectMocks
    private CountryServiceImpl countryService;

    @Test
    void shouldSavedCountrySuccessfully() {
        List<City> cities = new ArrayList<>();
        final Country country = new Country(1L, "Ukraine", null);
        cities.add(new City(null, "Chernivtsi", country));
        country.setCities(cities);
        given(countryRepository.findByName(country.getName())).willReturn(Optional.empty());
        given(countryRepository.save(country)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Country savedCountry = countryService.addCountry(country);

        assertThat(savedCountry).isNotNull();

        verify(countryRepository).save(any(Country.class));
    }

    @Test
    void shouldFindCountryByNameSuccessfully() {
        final String name = "Ukraine";
        List<City> cities = new ArrayList<>();
        final Country country = new Country(1L, "Ukraine", null);
        cities.add(new City(null, "Chernivtsi", country));
        country.setCities(cities);

        given(countryRepository.findByName(country.getName())).willReturn(Optional.of(country));

        final Country expected = countryService.findCountryByName(name);

        assertThat(expected).isNotNull();
    }

    @Test
    public void checkUpdateCountrySuccess() {
        List<City> cities = new ArrayList<>();
        final Country country = new Country(1L, "Ukraine", null);
        cities.add(new City(null, "Chernivtsi", country));
        country.setCities(cities);

        given(countryRepository.save(country)).willReturn(country);

        final Country expected = countryService.updateCountry(country);

        assertThat(expected).isNotNull();

        verify(countryRepository).save(any(Country.class));
    }

    @Test
    public void checkFindAllReturnSuccess() {
        List<City> cities = new ArrayList<>();
        final Country country1 = new Country(1L, "Ukraine", null);
        cities.add(new City(null, "Chernivtsi", country1));
        country1.setCities(cities);
        final Country country2 = new Country(2L, "Poland", null);
        List<Country> countries = new ArrayList<>();
        countries.add(country1);
        countries.add(country2);

        given(countryRepository.findAll()).willReturn(countries);

        List<Country> expected = countryService.findAllCountries();

        assertEquals(expected, countries);
    }

    @Test
    public void getByIdTestSuccess() {
        List<City> cities = new ArrayList<>();
        final Country country = new Country(1L, "Ukraine", null);
        cities.add(new City(null, "Chernivtsi", country));
        country.setCities(cities);

        given(countryRepository.findById(country.getId())).willReturn(Optional.of(country));

        final Country expected = countryService.findCountryById(country.getId());
        assertThat(expected).isNotNull();
    }

    @Test
    public void deleteCountryTest() {
        final Long countryId = 1L;

        countryService.deleteCountryById(countryId);
        countryService.deleteCountryById(countryId);

        verify(countryRepository, times(2)).deleteById(countryId);

    }
}
