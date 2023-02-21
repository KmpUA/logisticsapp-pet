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
public class OrderRequest {
    private Long cityFrom;
    private Long cityTo;
    private String cargoDescription;
    private Double cargoWeight;
    private Long trucker;
    private LocalDateTime startDeliver;
    private LocalDateTime endDeliver;
    private Boolean completed;
}
