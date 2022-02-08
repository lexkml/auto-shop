package com.kamelchukov.autoshop.service;

import com.kamelchukov.autoshop.exception.EntityNotFoundException;
import com.kamelchukov.autoshop.model.Dealership;
import com.kamelchukov.autoshop.model.dto.dealership.request.DealershipCreateRequest;
import com.kamelchukov.autoshop.repository.DealershipRepository;
import com.kamelchukov.autoshop.transformer.DealershipTransformer;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DealershipServiceTest {

    @InjectMocks
    private DealershipService dealershipService;

    @Mock
    private DealershipRepository dealershipRepository;

    public static final Dealership dealerShip = Dealership.builder()
            .id(1L)
            .name("Lime")
            .city("Voronezh")
            .build();

    @Test
    void createTest_successfulCase() {
        var request = DealershipCreateRequest.builder()
                .name(dealerShip.getName())
                .city(dealerShip.getCity())
                .build();

        var resultDealership = DealershipTransformer.fromDto(request);
        resultDealership.setId(dealerShip.getId());

        when(dealershipRepository.save(any(Dealership.class))).thenReturn(dealerShip);

        dealershipService.create(request);

        assertEquals(dealerShip, resultDealership);
    }

    @Test
    void findByIdTest_successfulCase() {
        when(dealershipRepository.findById(anyLong())).thenReturn(Optional.of(dealerShip));

        var resultDealership = dealershipService.findById(anyLong());

        assertEquals(dealerShip, resultDealership);
    }

    @Test
    void findByIdTest_ifDealershipNotFound() {
        when(dealershipRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> dealershipService.findById(anyLong()));
    }

    @Test
    void findAllTest_successfulCase() {
        var dealerships = List.of(DealershipServiceTest.dealerShip);
        when(dealershipRepository.findAll()).thenReturn(dealerships);

        var resultDealerships = dealershipService.findAll();

        assertThat(dealerships).containsAll(resultDealerships);
    }

    @Test
    void updateTest_successfulCase() {
        when(dealershipRepository.findById(anyLong())).thenReturn(Optional.of(dealerShip));
        when(dealershipRepository.save(any(Dealership.class))).thenReturn(dealerShip);

        var result = dealershipService.update(dealerShip.getId(), "dummy", "dummy");

        assertEquals(dealerShip, result);
    }

    @Test
    void deleteTest_successfulCase() {
        when(dealershipRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(dealershipRepository).deleteById(anyLong());

        dealershipService.delete(anyLong());

        verify(dealershipRepository).deleteById(anyLong());
    }

    @Test
    void deleteTest_ifDealershipNotFound() {
        when(dealershipRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () ->dealershipService.delete(anyLong()));
    }
}