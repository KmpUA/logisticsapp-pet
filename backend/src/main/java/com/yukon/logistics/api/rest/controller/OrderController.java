package com.yukon.logistics.api.rest.controller;

import com.yukon.logistics.model.dto.OrderRequest;
import com.yukon.logistics.model.dto.OrderResponse;
import com.yukon.logistics.model.mapper.OrderMapper;
import com.yukon.logistics.persistence.entity.Order;
import com.yukon.logistics.service.CityService;
import com.yukon.logistics.service.OrderService;
import com.yukon.logistics.service.TruckerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Long.parseLong;

@Controller
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final CityService cityService;
    private final TruckerService truckerService;

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

    @GetMapping("/city_from_name/{city_from_name}")
    public ResponseEntity<OrderResponse> getByCityFromName(@PathVariable("city_from_name") String cityFromName) {
        OrderResponse orderResponse = new OrderMapper().toResponse(orderService.findOrderByCityFrom(cityFromName));
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @GetMapping("/city_to_name/{city_to_name}")
    public ResponseEntity<OrderResponse> getByCityToName(@PathVariable("city_to_name") String cityToName) {
        OrderResponse orderResponse = new OrderMapper().toResponse(orderService.findOrderByCityTo(cityToName));
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @GetMapping("/trucker_name/{trucker_name}")
    public ResponseEntity<OrderResponse> getByTruckerName(@PathVariable("trucker_name") String truckerName) {
        OrderResponse orderResponse = new OrderMapper().toResponse(orderService.findOrderByTrucker(truckerName));
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderRequest orderRequest){
        Order order = new OrderMapper().toEntity(orderRequest,
                cityService.findCityById(orderRequest.getCityFrom()),
                cityService.findCityById(orderRequest.getCityTo()),
                truckerService.findTruckerById(orderRequest.getTrucker()));
        OrderResponse orderResponse = new OrderMapper().toResponse(orderService.addOrder(order));
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<OrderResponse> updateOrder(@RequestBody OrderRequest orderRequest){
        Order order = new OrderMapper().toEntity(orderRequest,
                cityService.findCityById(orderRequest.getCityFrom()),
                cityService.findCityById(orderRequest.getCityTo()),
                truckerService.findTruckerById(orderRequest.getTrucker()));
        OrderResponse orderResponse = new OrderMapper().toResponse(orderService.updateOrder(order));
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id){
        orderService.deleteOrderById(parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
