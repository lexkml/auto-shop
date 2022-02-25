package com.kamelchukov.autoshop.service;

import com.kamelchukov.autoshop.model.Price;
import com.kamelchukov.autoshop.model.dto.price.request.PriceCreateRequest;
import com.kamelchukov.autoshop.repository.PriceRepository;
import com.kamelchukov.autoshop.transformer.PriceTransformer;
import com.kamelchukov.common.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @InjectMocks
    private PriceService priceService;

    @Mock
    private PriceRepository priceRepository;

    public static final Price PRICE = Price.builder()
            .id(1L)
            .dealershipId(2L)
            .carId(5L)
            .price(5900)
            .build();

    @Test
    void createTest_successfulCase() {
        var request = PriceCreateRequest.builder()
                .dealershipId(PRICE.getDealershipId())
                .carId(PRICE.getCarId())
                .price(PRICE.getPrice())
                .build();

        var resultPrice = PriceTransformer.fromDto(request);
        resultPrice.setId(PRICE.getId());

        when(priceRepository.save(any(Price.class))).thenReturn(PRICE);

        priceService.create(request);

        assertEquals(PriceServiceTest.PRICE, resultPrice);
    }

    @Test
    void findByIdTest_successfulCase() {
        when(priceRepository.findById(any())).thenReturn(Optional.of(PRICE));

        var resultPrice = priceService.findById(anyLong());

        assertEquals(PRICE, resultPrice);
    }

    @Test
    void findByIdTest_ifPriceNotFound() {
        when(priceRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> priceService.findById(anyLong()));
    }

    @Test
    void findAllTest_successfulCase() {
        var prices = List.of(PriceServiceTest.PRICE);
        when(priceRepository.findAll()).thenReturn(prices);

        var resultPrices = priceService.findAll();

        assertThat(prices).containsAll(resultPrices);
    }

    @Test
    void updateTest_successfulCase() {
        when(priceRepository.findById(anyLong())).thenReturn(Optional.of(PRICE));
        when(priceRepository.save(any(Price.class))).thenReturn(PRICE);

        var result = priceService.update(PRICE.getId(), 1000);

        assertEquals(PRICE, result);
    }

    @Test
    void deleteTest_successfulCase() {
        when(priceRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(priceRepository).deleteById(anyLong());

        priceService.delete(anyLong());

        verify(priceRepository).deleteById(anyLong());
    }

    @Test
    void deleteTest_ifPriceNotFound() {
        when(priceRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> priceService.delete(anyLong()));
    }
}