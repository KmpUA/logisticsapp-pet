package com.yukon.logistics.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private String cityFrom;

    private String cityTo;

    private String truckerName;

    private String cargoDescription;

    private double cargoWeight;
}
