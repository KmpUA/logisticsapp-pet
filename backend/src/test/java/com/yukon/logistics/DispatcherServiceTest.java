package com.yukon.logistics;

import com.yukon.logistics.persistence.entity.Dispatcher;
import com.yukon.logistics.persistence.entity.Trucker;
import com.yukon.logistics.persistence.repository.DispatcherRepository;
import com.yukon.logistics.service.impl.DispatcherServiceImpl;
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
public class DispatcherServiceTest {
    @Mock
    private DispatcherRepository dispatcherRepository;
    @InjectMocks
    private DispatcherServiceImpl dispatcherService;

    private Dispatcher dispatcher;

    @BeforeEach
    void setup(){
        this.dispatcher = new Dispatcher(new ArrayList<>());
        this.dispatcher.setId(1L);
    }

    @Test
    void shouldSavedDispatcherSuccessfully() {
        given(dispatcherRepository.findById(dispatcher.getId())).willReturn(Optional.empty());
        given(dispatcherRepository.save(dispatcher)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Dispatcher savedDispatcher = dispatcherService.addDispatcher(dispatcher);

        assertThat(savedDispatcher).isNotNull();

        verify(dispatcherRepository).save(any(Dispatcher.class));
    }


    @Test
    public void checkUpdateDispatcherSuccess() {

        given(dispatcherRepository.save(dispatcher)).willReturn(dispatcher);

        final Dispatcher expected = dispatcherService.updateDispatcher(dispatcher);

        assertThat(expected).isNotNull();

        verify(dispatcherRepository).save(any(Dispatcher.class));
    }

    @Test
    public void checkFindAllReturnSuccess() {
        List<Dispatcher> dispatchers = new ArrayList<>();
        Dispatcher dispatcher1 = new Dispatcher();
        dispatcher1.setTruckers(dispatcher.getTruckers());
        dispatcher1.setId(2L);
        dispatchers.add(dispatcher);
        dispatchers.add(dispatcher1);

        given(dispatcherRepository.findAll()).willReturn(dispatchers);

        List<Dispatcher> expected = dispatcherService.findAllDispatchers();

        assertEquals(expected, dispatchers);
    }

    @Test
    public void getByTruckerTestSuccess() {
        Trucker trucker = new Trucker();
        trucker.setId(1L);
        dispatcher.setTruckers(List.of(trucker));
        given(dispatcherRepository.findByTruckers(trucker.getId())).willReturn(Optional.of(dispatcher));

        final Dispatcher expected = dispatcherService.findDispatcherByTrucker(trucker.getId());
        assertThat(expected).isNotNull();
    }

    @Test
    public void deleteTruckerTest() {
        final Long dispatcherId = 1L;

        dispatcherService.deleteDispatcherById(dispatcherId);
        dispatcherService.deleteDispatcherById(dispatcherId);

        verify(dispatcherRepository, times(2)).deleteById(dispatcherId);
    }
}
