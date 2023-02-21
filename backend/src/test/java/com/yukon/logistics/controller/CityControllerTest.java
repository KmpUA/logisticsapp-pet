package com.yukon.logistics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yukon.logistics.api.rest.controller.CityController;
import com.yukon.logistics.model.dto.CityRequest;
import com.yukon.logistics.persistence.entity.City;
import com.yukon.logistics.persistence.entity.Country;
import com.yukon.logistics.service.impl.CityServiceImpl;
import com.yukon.logistics.service.impl.CountryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CityController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class CityControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CountryServiceImpl countryService;

    @MockBean
    private CityServiceImpl cityService;

    @Autowired
    private ObjectMapper objectMapper;
    private static City city1;
    private static City city2;
    @BeforeEach
    void init() {
        Country country = new Country(1L, "country_1", null);
        city1 = new City(1L, "city_1", country);
        city2 = new City(2L, "city_2", country);
    }

    @Test
    void getAllTest() throws Exception {
        List<City> expected = new ArrayList<>();
        expected.add(city1);
        expected.add(city2);

        when(cityService.findAllCities()).thenReturn(expected);

        this.mockMvc.perform(get("/cities/all")).andExpect(status().isOk());
    }

    @Test
    void getByIdTest() throws Exception {
        City expected = city1;
        when(cityService.findCityById(expected.getId())).thenReturn(expected);
        this.mockMvc.perform(get("/cities/" + expected.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()))
                .andExpect(jsonPath("$.cityName").value(expected.getName()));
    }

    @Test
    void deleteByIdTest() throws Exception {
        doNothing().when(cityService).deleteCityById(1L);

        this.mockMvc.perform(delete("/cities/1")).andExpect(status().isOk());
        verify(cityService, times(1)).deleteCityById(1L);
    }

    @Test
    void addCityTest() throws Exception {
        City expected = city1;
        when(cityService.addCity(any(City.class))).thenReturn(expected);

        CityRequest cityRequest = new CityRequest(city1.getName(), city1.getCountry().getName());

        this.mockMvc.perform(post("/cities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cityRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cityName").value(expected.getName()))
                .andExpect(jsonPath("$.countyName").value(expected.getCountry().getName()));
    }

    @Test
    void updateCityTest() throws Exception {
        City expected = city1;
        when(cityService.updateCity(any(City.class))).thenReturn(expected);

        CityRequest cityRequest = new CityRequest(city1.getName(), city1.getCountry().getName());

        this.mockMvc.perform(put("/cities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cityRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cityName").value(expected.getName()))
                .andExpect(jsonPath("$.countyName").value(expected.getCountry().getName()));
    }
}
