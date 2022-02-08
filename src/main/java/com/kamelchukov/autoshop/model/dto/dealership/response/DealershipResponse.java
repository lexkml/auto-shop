package com.kamelchukov.autoshop.model.dto.dealership.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DealershipResponse {
    private Long id;
    private String name;
    private String city;
}
