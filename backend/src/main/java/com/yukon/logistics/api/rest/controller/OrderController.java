package com.yukon.logistics.api.rest.controller;

import com.yukon.logistics.model.dto.OrderResponse;
import com.yukon.logistics.model.mapper.OrderMapper;
import com.yukon.logistics.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> getAll() {
        List<OrderResponse> orderResponseList = new OrderMapper().toListResponse(orderService.findAllOrders());
        return new ResponseEntity<>(orderResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable("id") Long id) {
        OrderResponse orderResponse = new OrderMapper().toResponse(orderService.findOrderById(id));
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @GetMapping("/{city_from_name}")
    public ResponseEntity<OrderResponse> getByCityFromName(@PathVariable("city_from_name") String cityFromName) {
        OrderResponse orderResponse = new OrderMapper().toResponse(orderService.findOrderByCityFrom(cityFromName));
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @GetMapping("/{city_to_name}")
    public ResponseEntity<OrderResponse> getByCityToName(@PathVariable("city_to_name") String cityToName) {
        OrderResponse orderResponse = new OrderMapper().toResponse(orderService.findOrderByCityTo(cityToName));
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @GetMapping("/{trucker_name}")
    public ResponseEntity<OrderResponse> getByTruckerName(@PathVariable("trucker_name") String truckerName) {
        OrderResponse orderResponse = new OrderMapper().toResponse(orderService.findOrderByTrucker(truckerName));
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }
}
