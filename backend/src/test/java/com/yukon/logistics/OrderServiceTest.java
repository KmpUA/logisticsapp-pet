package com.yukon.logistics;

import com.yukon.logistics.persistence.entity.*;
import com.yukon.logistics.persistence.entity.Order;
import com.yukon.logistics.persistence.repository.OrderRepository;
import com.yukon.logistics.service.impl.OrderServiceImpl;
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
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;
    private City cityTo, cityFrom;

    @BeforeEach
    void setup(){
        Country country = new Country(1L, "Ukraine", new ArrayList<>());
        this.cityFrom = new City(1L, "Chernivtsi", country);
        this.cityTo = new City(2L, "Lviv", country);
        this.order = new Order();
        this.order = Order.builder().id(1L).from(this.cityFrom).to(this.cityTo).cargoWeight(10000).build();
    }

    @Test
    void shouldSavedOrderSuccessfully() {
        given(orderRepository.findById(order.getId())).willReturn(Optional.empty());
        given(orderRepository.save(order)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Order savedOrder = orderService.addOrder(order);

        assertThat(savedOrder).isNotNull();

        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void shouldFindOrderByCityFromSuccessfully() {
        final var name = "Chernivtsi";
        given(orderRepository.findByFromName(this.cityFrom.getName())).willReturn(Optional.of(order));

        final Order expected = orderService.findOrderByCityFrom(name);

        assertThat(expected).isNotNull();
    }

    @Test
    void shouldFindOrderByCityToSuccessfully() {
        final var name = "Lviv";
        given(orderRepository.findByToName(this.cityTo.getName())).willReturn(Optional.of(order));

        final Order expected = orderService.findOrderByCityTo(name);

        assertThat(expected).isNotNull();
    }

    @Test
    public void checkUpdateOrderSuccess() {
        given(orderRepository.save(order)).willReturn(order);

        final Order expected = orderService.updateOrder(order);

        assertThat(expected).isNotNull();

        verify(orderRepository).save(any(Order.class));
    }

    @Test
    public void checkFindAllReturnSuccess() {
        List<Order> orders = new ArrayList<>();
        Order order1 = Order.builder().id(2L).from(order.getTo()).to(order.getFrom()).cargoWeight(5000).build();
        orders.add(order);
        orders.add(order1);
        given(orderRepository.findAll()).willReturn(orders);

        List<Order> expected = orderService.findAllOrders();

        assertEquals(expected, orders);
    }

    @Test
    public void getByIdTestSuccess() {
        given(orderRepository.findById(order.getId())).willReturn(Optional.of(order));

        final Order expected = orderService.findOrderById(order.getId());
        assertThat(expected).isNotNull();
    }

    @Test
    public void getByTruckerIdTestSuccess() {
        Trucker trucker = new Trucker();
        trucker.setId(1L);
        order.setTrucker(trucker);
        given(orderRepository.findByTruckerId(order.getTrucker().getId())).willReturn(Optional.of(order));

        final Order expected = orderService.findOrderByTruckerId(trucker.getId());
        assertThat(expected).isNotNull();
    }

    @Test
    public void deleteOrderTest() {
        final Long orderId = 1L;

        orderService.deleteOrderById(orderId);
        orderService.deleteOrderById(orderId);

        verify(orderRepository, times(2)).deleteById(orderId);

    }
}
