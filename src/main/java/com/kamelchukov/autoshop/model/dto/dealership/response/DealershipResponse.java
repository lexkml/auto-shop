package com.kamelchukov.autoshop.model.dto.dealership.response;

import com.kamelchukov.autoshop.model.Price;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class DealershipResponse {
    private Long id;
    private String name;
    private String city;
    private Set<Price> prices;
}
