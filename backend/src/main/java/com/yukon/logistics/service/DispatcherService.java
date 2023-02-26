package com.yukon.logistics.service;

import com.yukon.logistics.persistence.entity.Dispatcher;

import java.util.List;

public interface DispatcherService {
    Dispatcher findDispatcherById(Long id);
    Dispatcher addDispatcher(Dispatcher dispatcher);
    List<Dispatcher> findAllDispatchers();
    Dispatcher updateDispatcher(Dispatcher dispatcher);
    void deleteDispatcherById(Long id);
    Dispatcher findDispatcherByTrucker(Long id);
}
