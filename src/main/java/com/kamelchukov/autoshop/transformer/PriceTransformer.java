package com.kamelchukov.autoshop.transformer;

import com.kamelchukov.autoshop.model.Price;
import com.kamelchukov.autoshop.model.dto.price.request.PriceCreateRequest;
import com.kamelchukov.autoshop.model.dto.price.responce.PriceResponse;

public final class PriceTransformer {
    private PriceTransformer() {
    }

    public static Price fromDto(PriceCreateRequest request) {
        return Price.builder()
                .dealershipId(request.getDealershipId())
                .carId(request.getCarId())
                .price(request.getPrice())
                .build();
    }

    public static PriceResponse toResponse(Price price) {
        return PriceResponse.builder()
                .id(price.getId())
                .dealershipId(price.getDealershipId())
                .carId(price.getCarId())
                .price(price.getPrice())
                .build();
    }
}
