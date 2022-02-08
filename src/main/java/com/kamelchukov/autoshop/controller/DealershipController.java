package com.kamelchukov.autoshop.controller;

import com.kamelchukov.autoshop.model.dto.dealership.request.DealershipCreateRequest;
import com.kamelchukov.autoshop.model.dto.dealership.response.DealershipResponse;
import com.kamelchukov.autoshop.service.DealershipService;
import com.kamelchukov.autoshop.transformer.DealershipTransformer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Dealership controller")
@AllArgsConstructor
public class DealershipController {

    private DealershipService service;

    @PostMapping("/dealerships")
    @Operation(summary = "Create dealership")
    public DealershipResponse create(@RequestBody DealershipCreateRequest request) {
        return DealershipTransformer.toResponse(service.create(request));
    }

    @GetMapping("/dealerships/{id}")
    @Operation(summary = "Find dealership by ID")
    public DealershipResponse findById(@PathVariable Long id) {
        return DealershipTransformer.toResponse(service.findById(id));
    }

    @GetMapping("/dealerships")
    @Operation(summary = "Find all dealerships")
    public List<DealershipResponse> findAll() {
        return service.findAll().stream().map(DealershipTransformer::toResponse).collect(Collectors.toList());
    }

    @PutMapping("/dealerships/{id}")
    @Operation(summary = "Update dealership")
    public DealershipResponse update(@PathVariable Long id, @RequestParam String name, @RequestParam(required = false) String city) {
        return DealershipTransformer.toResponse(service.update(id, name, city));
    }

    @DeleteMapping("/dealerships/{id}")
    @Operation(summary = "Delete dealership")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
