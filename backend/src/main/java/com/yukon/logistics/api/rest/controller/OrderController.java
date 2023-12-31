package com.yukon.logistics.api.rest.controller;

import com.yukon.logistics.model.dto.OrderRequest;
import com.yukon.logistics.model.dto.OrderResponse;
import com.yukon.logistics.model.mapper.OrderMapper;
import com.yukon.logistics.persistence.entity.Customer;
import com.yukon.logistics.persistence.entity.Order;
import com.yukon.logistics.persistence.entity.Trucker;
import com.yukon.logistics.service.CityService;
import com.yukon.logistics.service.CustomerService;
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
    private final CustomerService customerService;
    private final OrderMapper orderMapper;

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> getAll() {
        List<OrderResponse> orderResponseList = orderMapper.toListResponse(orderService.findAllOrders(), true, true);
        return new ResponseEntity<>(orderResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable("id") Long id) {
        OrderResponse orderResponse = orderMapper.toResponse(orderService.findOrderById(id), true, true);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @GetMapping("/city_from_name/{city_from_name}")
    public ResponseEntity<OrderResponse> getByCityFromName(@PathVariable("city_from_name") String cityFromName) {
        OrderResponse orderResponse = orderMapper.toResponse(orderService.findOrderByCityFrom(cityFromName), true, true);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @GetMapping("/city_to_name/{city_to_name}")
    public ResponseEntity<OrderResponse> getByCityToName(@PathVariable("city_to_name") String cityToName) {
        OrderResponse orderResponse = orderMapper.toResponse(orderService.findOrderByCityTo(cityToName), true, true);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @GetMapping("/trucker_id/{trucker_id}")
    public ResponseEntity<List<OrderResponse>> getByTruckerId(@PathVariable("trucker_id") String truckerId) {
        List<OrderResponse> orderResponse = new OrderMapper().toListResponse(orderService
                .findOrdersByTruckerId(parseLong(truckerId)), false, true);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderRequest orderRequest){
        Order order = orderMapper.toEntity(orderRequest,
                cityService.findCityById(orderRequest.getCityFrom()),
                cityService.findCityById(orderRequest.getCityTo()), null,
                customerService.findCustomerById(orderRequest.getCustomer()));
        order.setCompleted(false);
        OrderResponse orderResponse = orderMapper.toResponse(orderService.addOrder(order), true, true);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable("id") String id,
                                                     @RequestBody OrderRequest orderRequest) {
        Trucker trucker = null;
        if(orderRequest.getTrucker() != null) {
            trucker = truckerService.findTruckerById(orderRequest.getTrucker());
        }

        Customer customer = customerService.findCustomerById(orderRequest.getCustomer());
        Order order = orderMapper.toEntity(orderRequest,
                cityService.findCityById(orderRequest.getCityFrom()),
                cityService.findCityById(orderRequest.getCityTo()), trucker, customer);
        order.setId(parseLong(id));
        order.setCreated(orderService.findOrderById(parseLong(id)).getCreated());
        OrderResponse orderResponse = orderMapper.toResponse(orderService.updateOrder(order), true, true);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id){
        orderService.deleteOrderById(parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
