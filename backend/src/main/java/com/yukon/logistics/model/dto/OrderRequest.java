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
public class OrderRequest {
    //private Long id;
    private Long cityFrom;
    private Long cityTo;
    private Long trucker;
    String cargoDescription;
    double cargoWeight;
}
