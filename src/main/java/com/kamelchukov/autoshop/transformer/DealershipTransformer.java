package com.kamelchukov.autoshop.transformer;

import com.kamelchukov.autoshop.model.Dealership;
import com.kamelchukov.autoshop.model.dto.dealership.request.DealershipCreateRequest;
import com.kamelchukov.autoshop.model.dto.dealership.response.DealershipResponse;

public final class DealershipTransformer {
    private DealershipTransformer() {
    }

    public static Dealership fromDto(DealershipCreateRequest request) {
        return Dealership.builder()
                .name(request.getName())
                .city(request.getCity())
                .build();
    }

    public static DealershipResponse toResponse(Dealership dealership) {
        return DealershipResponse.builder()
                .id(dealership.getId())
                .name(dealership.getName())
                .city(dealership.getCity())
                .prices(dealership.getPrices())
                .build();
    }
}
