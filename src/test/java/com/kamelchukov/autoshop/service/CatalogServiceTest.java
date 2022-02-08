package com.kamelchukov.autoshop.service;

import com.kamelchukov.autoshop.model.Catalog;
import com.kamelchukov.autoshop.model.Dealership;
import com.kamelchukov.autoshop.model.Price;
import com.kamelchukov.autoshop.model.dto.car.CarDto;
import com.kamelchukov.autoshop.model.dto.person.PersonDto;
import com.kamelchukov.autoshop.service.api.AutoCatalogApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatalogServiceTest {

    @InjectMocks
    private CatalogService catalogService;

    @Mock
    private DealershipService dealershipService;

    @Mock
    private PriceService priceService;

    @Mock
    private AutoCatalogApi api;

    public static final Dealership dealerShip = Dealership.builder()
            .id(1L)
            .name("Lime")
            .city("Voronezh")
            .build();

    public static final List<Price> prices = List.of(Price.builder()
            .id(3L)
            .price(2500)
            .carId(8L)
            .dealershipId(1L)
            .build());

    public static final List<PersonDto> persons = List.of(new PersonDto());

    public static final List<CarDto> cars = List.of(new CarDto());

    @Test
    void findCatalogByIdTest_successfulCase() {
        when(dealershipService.findById(anyLong())).thenReturn(dealerShip);
        when(priceService.findPricesByDealershipId(anyLong())).thenReturn(prices);
        when(api.findAllCars()).thenReturn(cars);

        catalogService.findCatalogById(anyLong());

        assertEquals(prices.get(0).getCarId(), 8L);
        assertThat(cars.get(0).getId()).isEqualTo(null);
    }

    @Test
    void findAllCatalogsTest_successfulCase() {
        var catalog = Catalog.builder()
                .dealership(dealerShip)
                .build();

        when(dealershipService.findAll()).thenReturn(List.of(dealerShip));
        when(dealershipService.findById(anyLong())).thenReturn(dealerShip);

        catalogService.findAllCatalogs();

        assertEquals(catalog.getDealership(), dealerShip);

    }
}