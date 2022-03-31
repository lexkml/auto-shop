package com.kamelchukov.autoshop.service;

import com.kamelchukov.autoshop.model.Price;
import com.kamelchukov.autoshop.model.dto.price.request.PriceCreateRequest;
import com.kamelchukov.autoshop.repository.PriceRepository;
import com.kamelchukov.autoshop.transformer.PriceTransformer;
import com.kamelchukov.common.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PriceService {

    private PriceRepository repository;

    public Price create(PriceCreateRequest request) {
        return repository.save(PriceTransformer.fromDto(request));
    }

    public Price findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> {
                    throw new EntityNotFoundException("Price with id = " + id + " not found");
                });
    }

    public List<Price> findAll() {
        List<Price> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    public Price update(Long id, int newPrice) {
        Price price = findById(id);
        price.setPrice(newPrice);
        return repository.save(price);
    }

    public void remove(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Price with id = " + id + " not found");
        }
    }
}
