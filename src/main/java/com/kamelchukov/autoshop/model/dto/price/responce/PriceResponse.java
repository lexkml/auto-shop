package com.kamelchukov.autoshop.model.dto.price.responce;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceResponse {
    private Long id;
    private Long dealershipId;
    private Long carId;
    private int price;
}
