package com.yukon.logistics.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.yukon.logistics.persistence.entity.Condition;
import com.yukon.logistics.persistence.entity.Truck;
import com.yukon.logistics.persistence.repository.TruckRepository;
import com.yukon.logistics.service.impl.TruckServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TruckServiceTest {

    @Mock
    private static TruckRepository truckRepository;
    private static TruckServiceImpl truckService;
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

        truckService = new TruckServiceImpl(truckRepository);
    }

    @Test
    void addTruckTest() {
        Truck expected = truck1;
        when(truckRepository.save(expected)).thenReturn(expected);

        Truck actual = truckService.addTruck(expected);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findAllTrucksTest() {
        List<Truck> expected = new ArrayList<>();
        expected.add(truck1);
        expected.add(truck2);
        expected.add(truck3);

        when(truckRepository.findAll()).thenReturn(expected);

        List<Truck> actual = truckService.findAllTrucks();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findTruckByIdTest() {
        Truck expected = truck1;
        when(truckRepository.findById(expected.getId())).thenReturn(Optional.of(expected));

        Truck actual = truckService.findTruckById(expected.getId());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findTruckByLicencePlateTest() {
        Truck expected = truck2;
        when(truckRepository.findByLicensePlate(expected.getLicensePlate())).thenReturn(Optional.of(expected));

        Truck actual = truckService.findByLicensePlate(expected.getLicensePlate());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findTruckByVinCodeTest() {
        Truck expected = truck3;
        when(truckRepository.findByVinCode(expected.getVinCode())).thenReturn(Optional.of(expected));

        Truck actual = truckService.findTruckByVinCode(expected.getVinCode());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void findAllTrucksByModelTest() {
        List<Truck> expected = new ArrayList<>();
        expected.add(truck2);
        expected.add(truck3);

        when(truckRepository.findAllByModel(truck2.getModel())).thenReturn(expected);

        List<Truck> actual = truckService.findAllTrucksByModel(truck2.getModel());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteTruckByIdTest() {
        when(truckRepository.findById(truck1.getId())).thenReturn(Optional.of(truck1));
        doNothing().when(truckRepository).deleteById(truck1.getId());

        truckService.deleteTruckById(truck1.getId());
        verify(truckRepository, times(1)).deleteById(truck1.getId());
    }
}
