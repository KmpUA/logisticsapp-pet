package com.yukon.logistics.model.dto;

import com.yukon.logistics.persistence.entity.City;
import com.yukon.logistics.persistence.entity.Trucker;
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
    private Long id;
    private City cityFrom;

    private City cityTo;

    private Trucker trucker;
}
