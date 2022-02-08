package com.kamelchukov.autoshop.service;

import com.kamelchukov.autoshop.model.Catalog;
import com.kamelchukov.autoshop.model.Dealership;
import com.kamelchukov.autoshop.model.Price;
import com.kamelchukov.autoshop.service.api.AutoCatalogApi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CatalogService {

    private DealershipService dealershipService;
    private PriceService priceService;
    private AutoCatalogApi api;

    public Catalog findCatalogById(Long id) {
        var dealership = dealershipService.findById(id);
        var prices = priceService.findPricesByDealershipId(dealership.getId());
        var persons = api.findAllPersons();

        var listIdsCar = prices.stream()
                .map(Price::getCarId)
                .collect(toList());

        var cars = api.findAllCars().stream()
                .filter(car -> listIdsCar.contains(car.getId()))
                .collect(toList());

        return Catalog.builder()
                .dealership(dealership)
                .cars(cars)
                .prices(prices)
                .persons(persons)
                .build();
    }

    public List<Catalog> findAllCatalogs() {
        var dealerShips = dealershipService.findAll();
        List<Catalog> catalogList = new ArrayList<>();

        for (Dealership dealerShip : dealerShips) {
            var catalog = findCatalogById(dealerShip.getId());
            catalogList.add(catalog);
        }

        return catalogList;
    }
}
