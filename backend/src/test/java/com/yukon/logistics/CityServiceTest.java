package com.yukon.logistics;

import com.yukon.logistics.persistence.entity.City;
import com.yukon.logistics.persistence.entity.Country;
import com.yukon.logistics.persistence.repository.CityRepository;
import com.yukon.logistics.service.impl.CityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
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
public class CityServiceTest {
    @Mock
    private CityRepository cityRepository;
    @InjectMocks
    private CityServiceImpl cityService;

    private City city;

    @BeforeEach
    void setup(){
        Country country = new Country(1L, "Ukraine", new ArrayList<>());
        this.city = new City(1L, "Chernivtsi", country);
    }

    @Test
    void shouldSavedCitySuccessfully() {
        given(cityRepository.findByName(city.getName())).willReturn(Optional.empty());
        given(cityRepository.save(city)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        City savedCity = cityService.addCity(city);

        assertThat(savedCity).isNotNull();

        verify(cityRepository).save(any(City.class));
    }

    @Test
    void shouldFindCityByNameSuccessfully() {
        final var name = "Chernivtsi";
        given(cityRepository.findByName(city.getName())).willReturn(Optional.of(city));

        final City expected = cityService.findCityByName(name);

        assertThat(expected).isNotNull();
    }

    @Test
    public void checkUpdateCitySuccess() {
        given(cityRepository.save(city)).willReturn(city);

        final City expected = cityService.updateCity(city);

        assertThat(expected).isNotNull();

        verify(cityRepository).save(any(City.class));
    }

    @Test
    public void checkFindAllReturnSuccess() {
        List<City> cities = new ArrayList<>();
        City city2 = new City(2L, "Kyiv", city.getCountry());
        cities.add(city);
        cities.add(city2);
        given(cityRepository.findAll()).willReturn(cities);

        List<City> expected = cityService.findAllCities();

        assertEquals(expected, cities);
    }

    @Test
    public void getByIdTestSuccess() {
        given(cityRepository.findById(city.getId())).willReturn(Optional.of(city));

        final City expected = cityService.findCityById(city.getId());
        assertThat(expected).isNotNull();
    }

    @Test
    public void deleteCityTest() {
        final Long cityId = 1L;

        cityService.deleteCityById(cityId);
        cityService.deleteCityById(cityId);

        verify(cityRepository, times(2)).deleteById(cityId);

    }
}
