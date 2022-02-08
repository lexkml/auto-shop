package com.kamelchukov.autoshop.controller;

import com.kamelchukov.autoshop.model.dto.price.request.PriceCreateRequest;
import com.kamelchukov.autoshop.model.dto.price.responce.PriceResponse;
import com.kamelchukov.autoshop.service.PriceService;
import com.kamelchukov.autoshop.transformer.PriceTransformer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Tag(name = "Price controller")
public class PriceController {

    private PriceService service;

    @PostMapping("/prices")
    @Operation(summary = "Create price")
    public PriceResponse create(@RequestBody PriceCreateRequest request) {
        return PriceTransformer.toResponse(service.create(request));
    }

    @GetMapping("/prices/{id}")
    @Operation(summary = "Find price by ID")
    public PriceResponse findById(@PathVariable Long id) {
        return PriceTransformer.toResponse(service.findById(id));
    }

    @GetMapping("/prices")
    @Operation(summary = "Find all prices")
    public List<PriceResponse> findAll() {
        return service.findAll().stream().map(PriceTransformer::toResponse).collect(Collectors.toList());
    }

    @PutMapping("/prices/{id}")
    @Operation(summary = "Update price")
    public PriceResponse update(@PathVariable Long id, @RequestParam int newPrice) {
        return PriceTransformer.toResponse(service.update(id, newPrice));
    }

    @DeleteMapping("/prices/{id}")
    @Operation(summary = "Delete price")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
