package com.yukon.logistics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yukon.logistics.api.rest.controller.TruckController;
import com.yukon.logistics.model.dto.TruckRequest;
import com.yukon.logistics.persistence.entity.Condition;
import com.yukon.logistics.persistence.entity.Truck;
import com.yukon.logistics.service.impl.TruckServiceImpl;
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
@WebMvcTest(controllers = TruckController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class TruckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TruckServiceImpl truckService;

    @Autowired
    private ObjectMapper objectMapper;
    private static Truck truck1;

    private static Truck truck2;
    private static Truck truck3;
    @BeforeEach
    void init() {
        truck1 = new Truck(1L, "model_1", null, 111, 125,
                545, Condition.IN_REPAIR, "vin_1", "1111", null);
        truck2 = new Truck(2L, "model_2", null, 111, 125,
                545, Condition.IN_REPAIR, "vin_2", "2222", null);
        truck3 = new Truck(3L, "model_2", null, 111, 125,
                545, Condition.IN_REPAIR, "vin_3", "3333", null);
    }

    @Test
    void getAllTest() throws Exception {
        List<Truck> expected = new ArrayList<>();
        expected.add(truck1);
        expected.add(truck2);
        expected.add(truck3);

        when(truckService.findAllTrucks()).thenReturn(expected);

        this.mockMvc.perform(get("/trucks/all")).andExpect(status().isOk());
    }

    @Test
    void getByIdTest() throws Exception {
        Truck expected = truck1;
        when(truckService.findTruckById(expected.getId())).thenReturn(expected);
        this.mockMvc.perform(get("/trucks/" + expected.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()))
                .andExpect(jsonPath("$.licensePlate").value(expected.getLicensePlate()));
    }

    @Test
    void deleteByIdTest() throws Exception {
        doNothing().when(truckService).deleteTruckById(1L);

        this.mockMvc.perform(delete("/trucks/1")).andExpect(status().isOk());
        verify(truckService, times(1)).deleteTruckById(1L);
    }

    @Test
    void addTruckTest() throws Exception {
        Truck expected = truck1;
        when(truckService.addTruck(any(Truck.class))).thenReturn(expected);

        TruckRequest truckRequest = new TruckRequest(truck1.getCondition(), truck1.getModel(),
                truck1.getFuelConsumption(), truck1.getOrderCapacity(), truck1.getSpaceCapacity(),
                truck1.getVinCode(), truck1.getLicensePlate());

        this.mockMvc.perform(post("/trucks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(truckRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model").value(expected.getModel()))
                .andExpect(jsonPath("$.licensePlate").value(expected.getLicensePlate()));
    }

    @Test
    void updateTruckTest() throws Exception {
        Truck expected = truck1;
        when(truckService.updateTruck(any(Truck.class))).thenReturn(expected);

        TruckRequest truckRequest = new TruckRequest(truck1.getCondition(), truck1.getModel(),
                truck1.getFuelConsumption(), truck1.getOrderCapacity(), truck1.getSpaceCapacity(),
                truck1.getVinCode(), truck1.getLicensePlate());

        this.mockMvc.perform(put("/trucks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(truckRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value(expected.getModel()))
                .andExpect(jsonPath("$.licensePlate").value(expected.getLicensePlate()));
    }
}
