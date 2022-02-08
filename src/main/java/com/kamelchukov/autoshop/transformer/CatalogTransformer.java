package com.kamelchukov.autoshop.transformer;

import com.kamelchukov.autoshop.model.Catalog;
import com.kamelchukov.autoshop.model.dto.catalog.CatalogResponse;

public final class CatalogTransformer {
    private CatalogTransformer() {
    }

    public static CatalogResponse toCatalogResponse(Catalog catalog) {
        return CatalogResponse.builder()
                .city(catalog.getDealership().getCity())
                .name(catalog.getDealership().getName())
                .cars(CarPricePersonTransformer.toCarResponseList(
                        catalog.getCars(), catalog.getPrices(), catalog.getPersons()
                ))
                .build();
    }
}
