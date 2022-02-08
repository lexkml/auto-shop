package com.kamelchukov.autoshop.model.dto.price.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceCreateRequest {
    private Long dealershipId;
    private Long carId;
    private int price;
}
