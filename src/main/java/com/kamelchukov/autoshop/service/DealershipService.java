package com.kamelchukov.autoshop.service;

import com.kamelchukov.autoshop.exception.EntityNotFoundException;
import com.kamelchukov.autoshop.model.Dealership;
import com.kamelchukov.autoshop.model.dto.dealership.request.DealershipCreateRequest;
import com.kamelchukov.autoshop.repository.DealershipRepository;
import com.kamelchukov.autoshop.transformer.DealershipTransformer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DealershipService {

    private DealershipRepository repository;

    public Dealership create(DealershipCreateRequest request) {
        return repository.save(DealershipTransformer.fromDto(request));
    }

    public Dealership findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> {
                    throw new EntityNotFoundException("Dealership with id = " + id + " not found.");
                });
    }

    public List<Dealership> findAll() {
        List<Dealership> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    public Dealership update(Long id, String name, String city) {
        Dealership dealership = findById(id);
        dealership.setName(name);
        if (city != null) {
            dealership.setCity(city);
        }
        return repository.save(dealership);
    }

    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Dealership with id = " + id + " not found");
        }
    }
}
