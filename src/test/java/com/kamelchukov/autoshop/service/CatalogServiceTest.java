package com.kamelchukov.autoshop.service;

import com.kamelchukov.autoshop.api.AutoCatalogApi;
import com.kamelchukov.autoshop.model.Dealership;
import com.kamelchukov.autoshop.model.Price;
import com.kamelchukov.autoshop.model.dto.car.FullDataOfCarResponse;
import com.kamelchukov.autoshop.model.dto.catalog.CatalogResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatalogServiceTest {

    @InjectMocks
    private CatalogService catalogService;

    @Mock
    private DealershipService dealershipService;

    @Mock
    private AutoCatalogApi api;

    public static final Price PRICE = Price.builder()
            .id(5L)
            .dealershipId(1L)
            .carId(12L)
            .price(5000)
            .build();

    private static final FullDataOfCarResponse CAR = FullDataOfCarResponse.builder()
            .id(12L)
            .model("Toyota")
            .year("2012")
            .color("Blue")
            .personId(4L)
            .firstName("Ivan")
            .lastName("Ivanov")
            .build();

    private static final Dealership DEALERSHIP = Dealership.builder()
            .id(1L)
            .city("Moscow")
            .name("Motor-land")
            .prices(Set.of(PRICE))
            .build();

    public static final CatalogResponse CATALOG = CatalogResponse.builder()
            .city(DEALERSHIP.getCity())
            .name(DEALERSHIP.getName())
            .cars(List.of(CAR))
            .build();


    @Test
    void showCatalogByDealershipIdTest_successfulCase() {
        when(dealershipService.findById(anyLong())).thenReturn(DEALERSHIP);
        when(api.findFullDataAllOfCars()).thenReturn(List.of(CAR));

        var result = catalogService.showCatalogByDealershipId(anyLong());

        assertEquals(result, CATALOG);
    }

    @Test
    void showAllCatalogsOfAllDealershipsTest_successfulCase() {
        var spy = spy(new CatalogService(dealershipService, api));

        when(dealershipService.findAll()).thenReturn(List.of(DEALERSHIP));
        doReturn(null).when(spy).showCatalogByDealershipId(anyLong());

        spy.showAllCatalogsOfAllDealerships();

        verify(spy, atLeast(1)).showCatalogByDealershipId(anyLong());
    }
}