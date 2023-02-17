package com.yukon.logistics.model.dto;

import com.yukon.logistics.persistence.entity.Role;
import com.yukon.logistics.persistence.entity.Status;
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
public class TruckerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String imageUrl;
    private String phone;
    private Role role;
    private Status status;
    private List<Long> ordersId;
}
