package com.yukon.logistics.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private Long cityFrom;
    private Long cityTo;
    private String cargoDescription;
    private Double cargoWeight;
    private TruckerResponse trucker;
    private CustomerResponse customer;
    private LocalDateTime created;
    private LocalDateTime modify;
    private LocalDateTime startDeliver;
    private LocalDateTime endDeliver;
    private Boolean completed;
}
