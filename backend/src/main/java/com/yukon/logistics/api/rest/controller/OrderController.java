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

    @GetMapping("/trucker_id/{trucker_id}")
    public ResponseEntity<OrderResponse> getByTruckerName(@PathVariable("trucker_id") String truckerId) {
        OrderResponse orderResponse = new OrderMapper().toResponse(orderService
                .findOrderByTruckerId(parseLong(truckerId)));
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderRequest orderRequest){
        Order order = new OrderMapper().toEntity(orderRequest,
                cityService.findCityById(orderRequest.getCityFrom()),
                cityService.findCityById(orderRequest.getCityTo()), null,
                customerService.findCustomerById(orderRequest.getCustomer()));
        order.setCompleted(false);
        OrderResponse orderResponse = new OrderMapper().toResponse(orderService.addOrder(order));
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
        Order order = new OrderMapper().toEntity(orderRequest,
                cityService.findCityById(orderRequest.getCityFrom()),
                cityService.findCityById(orderRequest.getCityTo()), trucker, customer);
        order.setId(parseLong(id));
        order.setCreated(orderService.findOrderById(parseLong(id)).getCreated());
        OrderResponse orderResponse = new OrderMapper().toResponse(orderService.updateOrder(order));
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id){
        orderService.deleteOrderById(parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
