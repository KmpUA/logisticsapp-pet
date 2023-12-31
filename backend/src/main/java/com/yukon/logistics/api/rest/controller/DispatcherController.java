package com.yukon.logistics.api.rest.controller;

import com.yukon.logistics.model.dto.DispatcherRequest;
import com.yukon.logistics.model.dto.DispatcherResponse;
import com.yukon.logistics.model.mapper.DispatcherMapper;
import com.yukon.logistics.persistence.entity.Dispatcher;
import com.yukon.logistics.persistence.entity.Status;
import com.yukon.logistics.service.DispatcherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import static java.lang.Long.parseLong;

@Controller
@RequestMapping("/dispatchers")
@AllArgsConstructor
public class DispatcherController {
    private final DispatcherService dispatcherService;
    private final DispatcherMapper dispatcherMapper;

    @GetMapping("/all")
    public ResponseEntity<List<DispatcherResponse>> getAll(boolean includeTruckers) {
        List<DispatcherResponse> dispatcherResponseList = dispatcherMapper
                .toListResponse(dispatcherService.findAllDispatchers(), includeTruckers);
        return new ResponseEntity<>(dispatcherResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispatcherResponse> getById(@PathVariable("id") String id) {
        DispatcherResponse dispatcherResponse = dispatcherMapper
                .toResponse(dispatcherService.findDispatcherById(parseLong(id)), true);
        return new ResponseEntity<>(dispatcherResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        Dispatcher dispatcher = dispatcherService.findDispatcherById(parseLong(id));
        dispatcher.setStatus(Status.DELETED);
        dispatcherService.updateDispatcher(dispatcher);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DispatcherResponse> add(@RequestBody DispatcherRequest dispatcherRequest) {
        Dispatcher dispatcher = dispatcherMapper.toEntity(dispatcherRequest);
        DispatcherResponse dispatcherResponse = dispatcherMapper
                .toResponse(dispatcherService.addDispatcher(dispatcher), false);
        return new ResponseEntity<>(dispatcherResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DispatcherResponse> update(@PathVariable("id") String id,
                                                     @RequestBody DispatcherRequest dispatcherRequest) {
        Dispatcher dispatcher = dispatcherMapper.toEntity(dispatcherRequest);
        dispatcher.setId(parseLong(id));
        DispatcherResponse dispatcherResponse = dispatcherMapper
                .toResponse(dispatcherService.updateDispatcher(dispatcher), false);
        return new ResponseEntity<>(dispatcherResponse, HttpStatus.OK);
    }
}
