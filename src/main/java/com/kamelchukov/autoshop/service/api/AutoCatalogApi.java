package com.kamelchukov.autoshop.service.api;

import com.kamelchukov.autoshop.model.dto.car.CarDto;
import com.kamelchukov.autoshop.model.dto.person.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "autoCatalogApi", url = "http://localhost:8080")
public interface AutoCatalogApi {

    @GetMapping("/cars")
    List<CarDto> findAllCars();

    @GetMapping("/persons")
    List<PersonDto> findAllPersons();
}
