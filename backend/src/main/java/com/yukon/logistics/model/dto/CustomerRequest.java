package com.yukon.logistics.model.dto;

import com.yukon.logistics.persistence.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    private List<Order> orders;
}
