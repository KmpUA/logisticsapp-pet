package com.yukon.logistics;

import com.yukon.logistics.persistence.entity.Dispatcher;
import com.yukon.logistics.persistence.entity.Order;
import com.yukon.logistics.persistence.entity.Truck;
import com.yukon.logistics.persistence.entity.Trucker;
import com.yukon.logistics.persistence.repository.TruckerRepository;
import com.yukon.logistics.service.impl.TruckerServiceImpl;
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
public class TruckerServiceTest {
    @Mock
    private TruckerRepository truckerRepository;
    @InjectMocks
    private TruckerServiceImpl truckerService;

    private Trucker trucker;

    @BeforeEach
    void setup(){
        Truck truck = Truck.builder().id(1L).build();
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setId(1L);
        Order order = Order.builder().id(1L).build();
        this.trucker = new Trucker();
        this.trucker = Trucker.builder().truck(truck).dispatcher(dispatcher).orders(List.of(order)).build();
        trucker.setId(1L);
    }

    @Test
    void shouldSavedTruckSuccessfully() {
        given(truckerRepository.findById(trucker.getId())).willReturn(Optional.empty());
        given(truckerRepository.save(trucker)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Trucker savedTruck = truckerService.addTrucker(trucker);

        assertThat(savedTruck).isNotNull();

        verify(truckerRepository).save(any(Trucker.class));
    }

    @Test
    void shouldFindTruckerByTruckSuccessfully() {
        final var truckId = 1L;
        given(truckerRepository.findByTruck(trucker.getTruck().getId())).willReturn(Optional.of(trucker));

        final Trucker expected = truckerService.findTruckerByTruck(truckId);

        assertThat(expected).isNotNull();
    }

    @Test
    void shouldFindTruckByDispatcherSuccessfully() {
        final var dispatcherId = 1L;
        given(truckerRepository.findByDispatcher(trucker.getDispatcher().getId())).willReturn(Optional.of(List.of(trucker)));

        final List<Trucker> expected = truckerService.findTruckerByDispatcher(dispatcherId);

        assertThat(expected).isNotNull();
    }

    void shouldFindTruckByOrderSuccessfully() {
        final var orderId = 1L;
        Order order1 = Order.builder().id(1L).build();
        given(truckerRepository.findByOrders(order1.getId())).willReturn(Optional.of(trucker));

        final Trucker expected = truckerService.findTruckerByTruck(orderId);

        assertThat(expected).isNotNull();
    }

    @Test
    public void checkUpdateTruckSuccess() {
        given(truckerRepository.save(trucker)).willReturn(trucker);

        final Trucker expected = truckerService.updateTrucker(trucker);

        assertThat(expected).isNotNull();

        verify(truckerRepository).save(any(Trucker.class));
    }

    @Test
    public void checkFindAllReturnSuccess() {
        List<Trucker> truckers = new ArrayList<>();
        Trucker trucker1 = Trucker.builder().truck(trucker.getTruck()).dispatcher(trucker.getDispatcher()).orders(trucker.getOrders()).build();
        trucker1.setId(2L);
        truckers.add(trucker);
        truckers.add(trucker1);
        given(truckerRepository.findAll()).willReturn(truckers);

        List<Trucker> expected = truckerService.findAllTruckers();

        assertEquals(expected, truckers);
    }

    @Test
    public void getByIdTestSuccess() {
        given(truckerRepository.findById(trucker.getId())).willReturn(Optional.of(trucker));

        final Trucker expected = truckerService.findTruckerById(trucker.getId());
        assertThat(expected).isNotNull();
    }

    @Test
    public void deleteTruckTest() {
        final Long truckId = 1L;

        truckerService.deleteTruckerById(truckId);
        truckerService.deleteTruckerById(truckId);

        verify(truckerRepository, times(2)).deleteById(truckId);

    }
}
