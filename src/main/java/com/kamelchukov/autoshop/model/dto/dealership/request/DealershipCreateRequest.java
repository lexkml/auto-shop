package com.kamelchukov.autoshop.model.dto.dealership.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DealershipCreateRequest {
    private String name;
    private String city;
}
