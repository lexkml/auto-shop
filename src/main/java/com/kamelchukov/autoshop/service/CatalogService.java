package com.kamelchukov.autoshop.service;

import com.kamelchukov.autoshop.model.Dealership;
import com.kamelchukov.autoshop.model.Price;
import com.kamelchukov.autoshop.model.dto.catalog.CatalogResponse;
import com.kamelchukov.common.api.AutoCatalogApi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CatalogService {

    private DealershipService dealershipService;
    private AutoCatalogApi api;

    public CatalogResponse showCatalogByDealershipId(Long id) {
        var dealership = dealershipService.findById(id);
        var carsIds = dealership.getPrices().stream()
                .map(Price::getCarId)
                .toList();

        var cars = api.findFullDataAllOfCars().stream()
                .filter(car -> carsIds.contains(car.getId()))
                .collect(toList());

        return CatalogResponse.builder()
                .city(dealership.getCity())
                .name(dealership.getName())
                .cars(cars)
                .build();
    }

    public List<CatalogResponse> showAllCatalogsOfAllDealerships() {
        var dealerships = dealershipService.findAll();
        var catalogs = new ArrayList<CatalogResponse>();

        for (Dealership dealership : dealerships) {
            catalogs.add(showCatalogByDealershipId(dealership.getId()));
        }

        return catalogs;
    }
}
