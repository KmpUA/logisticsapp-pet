package com.yukon.logistics;

import com.yukon.logistics.persistence.entity.Customer;
import com.yukon.logistics.persistence.entity.Order;
import com.yukon.logistics.persistence.repository.CustomerRepository;
import com.yukon.logistics.service.impl.CustomerServiceImpl;
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
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer1, customer2;

    @BeforeEach
    void setup() {
        this.customer1 = new Customer(new ArrayList<>());
        this.customer2 = new Customer(new ArrayList<>());
        this.customer1.setId(1L);
        this.customer2.setId(2L);
    }

    @Test
    void shouldSavedCustomerSuccessfully() {
        given(customerRepository.findById(customer1.getId())).willReturn(Optional.empty());
        given(customerRepository.save(customer1)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Customer savedCustomer = customerService.addCustomer(customer1);

        assertThat(savedCustomer).isNotNull();

        verify(customerRepository).save(any(Customer.class));
    }


    @Test
    public void checkUpdateCustomerSuccess() {

        given(customerRepository.save(customer1)).willReturn(customer1);

        final Customer expected = customerService.updateCustomer(customer1);

        assertThat(expected).isNotNull();

        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    public void checkFindAllReturnSuccess() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        given(customerRepository.findAll()).willReturn(customers);

        List<Customer> expected = customerService.findAllCustomers();

        assertEquals(expected, customers);
    }

    @Test
    public void getByIdTestSuccess() {
        given(customerRepository.findById(customer1.getId())).willReturn(Optional.of(customer1));

        final Customer expected = customerService.findCustomerById(customer1.getId());
        assertThat(expected).isNotNull();
    }

    @Test
    public void getByOrderTestSuccess(){
        Order order = new Order();
        order.setId(1L);
        customer1.setOrders(List.of(order));
        given(customerRepository.findByOrders(order.getId())).willReturn(Optional.of(customer1));

        final Customer expected = customerService.findCustomerByOrder(order.getId());
        assertThat(expected).isEqualTo(customer1);
    }

    @Test
    public void deleteCustomerTest() {
        final Long customerId = 1L;

        customerService.deleteCustomerById(customerId);
        customerService.deleteCustomerById(customerId);

        verify(customerRepository, times(2)).deleteById(customerId);
    }
}
