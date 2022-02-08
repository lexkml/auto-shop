package com.kamelchukov.autoshop.service;

import com.kamelchukov.autoshop.exception.EntityNotFoundException;
import com.kamelchukov.autoshop.model.Price;
import com.kamelchukov.autoshop.model.dto.price.request.PriceCreateRequest;
import com.kamelchukov.autoshop.repository.PriceRepository;
import com.kamelchukov.autoshop.transformer.PriceTransformer;
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

    public static final Price price = Price.builder()
            .id(1L)
            .dealershipId(2L)
            .carId(5L)
            .price(5900)
            .build();

    @Test
    void createTest_successfulCase() {
        var request = PriceCreateRequest.builder()
                .dealershipId(price.getDealershipId())
                .carId(price.getCarId())
                .price(price.getPrice())
                .build();

        var resultPrice = PriceTransformer.fromDto(request);
        resultPrice.setId(price.getId());

        when(priceRepository.save(any(Price.class))).thenReturn(price);

        priceService.create(request);

        assertEquals(PriceServiceTest.price, resultPrice);
    }

    @Test
    void findByIdTest_successfulCase() {
        when(priceRepository.findById(any())).thenReturn(Optional.of(price));

        var resultPrice = priceService.findById(anyLong());

        assertEquals(price, resultPrice);
    }

    @Test
    void findByIdTest_ifPriceNotFound() {
        when(priceRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> priceService.findById(anyLong()));
    }

    @Test
    void findAllTest_successfulCase() {
        var prices = List.of(PriceServiceTest.price);
        when(priceRepository.findAll()).thenReturn(prices);

        var resultPrices = priceService.findAll();

        assertThat(prices).containsAll(resultPrices);
    }

    @Test
    void updateTest_successfulCase() {
        when(priceRepository.findById(anyLong())).thenReturn(Optional.of(price));
        when(priceRepository.save(any(Price.class))).thenReturn(price);

        var result = priceService.update(price.getId(), 1000);

        assertEquals(price, result);
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