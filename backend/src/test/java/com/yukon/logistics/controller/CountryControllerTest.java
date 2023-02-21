package com.yukon.logistics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yukon.logistics.api.rest.controller.CountryController;
import com.yukon.logistics.model.dto.CountryRequest;
import com.yukon.logistics.persistence.entity.Country;
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
@WebMvcTest(controllers = CountryController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class CountryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CountryServiceImpl countryService;

    @Autowired
    private ObjectMapper objectMapper;
    private static Country country1;
    private static Country country2;
    @BeforeEach
    void init() {
        country1 = new Country(1L, "country_1", null);
        country2 = new Country(2L, "country_2", null);
    }

    @Test
    void getAllTest() throws Exception {
        List<Country> expected = new ArrayList<>();
        expected.add(country1);
        expected.add(country2);

        when(countryService.findAllCountries()).thenReturn(expected);

        this.mockMvc.perform(get("/countries/all")).andExpect(status().isOk());
    }

    @Test
    void getByIdTest() throws Exception {
        Country expected = country1;
        when(countryService.findCountryById(expected.getId())).thenReturn(expected);
        this.mockMvc.perform(get("/countries/" + expected.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()))
                .andExpect(jsonPath("$.countryName").value(expected.getName()));
    }

    @Test
    void deleteByIdTest() throws Exception {
        doNothing().when(countryService).deleteCountryById(1L);

        this.mockMvc.perform(delete("/countries/1")).andExpect(status().isOk());
        verify(countryService, times(1)).deleteCountryById(1L);
    }

    @Test
    void addCountryTest() throws Exception {
        Country expected = country1;
        when(countryService.addCountry(any(Country.class))).thenReturn(expected);

        CountryRequest countryRequest = new CountryRequest(country1.getName());

        this.mockMvc.perform(post("/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(countryRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.countryName").value(expected.getName()));
    }

    @Test
    void updateCountryTest() throws Exception {
        Country expected = country1;
        when(countryService.updateCountry(any(Country.class))).thenReturn(expected);

        CountryRequest countryRequest = new CountryRequest(country1.getName());

        this.mockMvc.perform(put("/countries/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(countryRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryName").value(expected.getName()));
    }
}
