package com.kamelchukov.autoshop.controller;

import com.kamelchukov.autoshop.model.dto.catalog.CatalogResponse;
import com.kamelchukov.autoshop.service.CatalogService;
import com.kamelchukov.autoshop.transformer.CatalogTransformer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@AllArgsConstructor
public class CatalogController {

    private CatalogService service;

    @GetMapping("/catalog/{id}")
    CatalogResponse findCatalogById(@PathVariable Long id) {
        return CatalogTransformer.toCatalogResponse(service.findCatalogById(id));
    }

    @GetMapping("/catalogs")
    List<CatalogResponse> findAllCatalogs() {
        return service.findAllCatalogs().stream()
                .map(CatalogTransformer::toCatalogResponse)
                .collect(toList());
    }
}
