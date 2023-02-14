package com.yukon.logistics.service.impl;

import com.yukon.logistics.persistence.entity.Dispatcher;
import com.yukon.logistics.persistence.repository.DispatcherRepository;
import com.yukon.logistics.service.DispatcherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DispatcherServiceImpl implements DispatcherService {
    DispatcherRepository dispatcherRepository;

    @Override
    public Dispatcher addDispatcher(Dispatcher dispatcher) {
        return dispatcherRepository.save(dispatcher);
    }

    @Override
    public List<Dispatcher> findAllDispatchers() {
        return dispatcherRepository.findAll();
    }

    @Override
    public Dispatcher updateDispatcher(Dispatcher dispatcher) {
        return dispatcherRepository.save(dispatcher);
    }

    @Override
    public void deleteDispatcherById(Long id) {
        dispatcherRepository.deleteById(id);
    }

    @Override
    public Dispatcher findDispatcherByTrucker(Long id) {
        return dispatcherRepository.findByTruckers(id).orElseThrow();
    }
}
