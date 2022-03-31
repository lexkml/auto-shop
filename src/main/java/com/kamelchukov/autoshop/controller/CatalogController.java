package com.kamelchukov.autoshop.controller;

import com.kamelchukov.autoshop.model.dto.catalog.CatalogResponse;
import com.kamelchukov.autoshop.service.CatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Catalog controller")
@AllArgsConstructor
public class CatalogController {

    private CatalogService service;

    @GetMapping("/catalogs/{id}")
    @Operation(summary = "Show catalog by dealership id")
    CatalogResponse showCatalogByDealershipId(@PathVariable Long id) {
        return service.showCatalogByDealershipId(id);
    }

    @GetMapping("/catalogs")
    @Operation(summary = "Show all catalogs of all dealerships")
    List<CatalogResponse> showAllCatalogs() {
        return service.showAllCatalogsOfAllDealerships();
    }
}
