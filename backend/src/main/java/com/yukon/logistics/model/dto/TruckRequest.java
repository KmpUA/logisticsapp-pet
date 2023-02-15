package com.yukon.logistics.model.dto;

import com.yukon.logistics.persistence.entity.Condition;
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
public class TruckRequest {
    private Long id;
    private Condition condition;
}
