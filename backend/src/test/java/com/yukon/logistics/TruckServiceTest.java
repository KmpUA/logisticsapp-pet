package com.yukon.logistics;

import com.yukon.logistics.persistence.entity.Truck;
import com.yukon.logistics.persistence.repository.TruckRepository;
import com.yukon.logistics.service.impl.TruckServiceImpl;
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
public class TruckServiceTest {
    @Mock
    private TruckRepository truckRepository;
    @InjectMocks
    private TruckServiceImpl truckService;

    private Truck truck;

    @BeforeEach
    void setup(){
        this.truck = new Truck();
        this.truck = Truck.builder().id(1L).vinCode("123456").licensePlate("CE6713AO").build();
    }

    @Test
    void shouldSavedTruckSuccessfully() {
        given(truckRepository.findById(truck.getId())).willReturn(Optional.empty());
        given(truckRepository.save(truck)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Truck savedTruck = truckService.addTruck(truck);

        assertThat(savedTruck).isNotNull();

        verify(truckRepository).save(any(Truck.class));
    }

    @Test
    void shouldFindTruckByLicensePlateSuccessfully() {
        final var licensePlate = "CE6713AO";
        given(truckRepository.findByLicensePlate(truck.getLicensePlate())).willReturn(Optional.of(truck));

        final Truck expected = truckService.findByLicensePlate(licensePlate);

        assertThat(expected).isNotNull();
    }

    @Test
    void shouldFindTruckByVinCodeSuccessfully() {
        final var vin = "123456";
        given(truckRepository.findByVinCode(truck.getVinCode())).willReturn(Optional.of(truck));

        final Truck expected = truckService.findTruckByVinCode(vin);

        assertThat(expected).isNotNull();
    }

    @Test
    public void checkUpdateTruckSuccess() {
        given(truckRepository.save(truck)).willReturn(truck);

        final Truck expected = truckService.updateTruck(truck);

        assertThat(expected).isNotNull();

        verify(truckRepository).save(any(Truck.class));
    }

    @Test
    public void checkFindAllReturnSuccess() {
        List<Truck> trucks = new ArrayList<>();
        Truck truck1 = Truck.builder().id(2L).licensePlate("CE6731AO").vinCode("654321").build();
        trucks.add(truck);
        trucks.add(truck1);
        given(truckRepository.findAll()).willReturn(trucks);

        List<Truck> expected = truckService.findAllTrucks();

        assertEquals(expected, trucks);
    }

    @Test
    public void getByIdTestSuccess() {
        given(truckRepository.findById(truck.getId())).willReturn(Optional.of(truck));

        final Truck expected = truckService.findTruckById(truck.getId());
        assertThat(expected).isNotNull();
    }

    @Test
    public void deleteTruckTest() {
        final Long truckId = 1L;

        truckService.deleteTruckById(truckId);
        truckService.deleteTruckById(truckId);

        verify(truckRepository, times(2)).deleteById(truckId);

    }
}
