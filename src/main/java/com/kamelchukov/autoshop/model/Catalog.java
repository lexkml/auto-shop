package com.kamelchukov.autoshop.model;

import com.kamelchukov.autoshop.model.dto.car.CarDto;
import com.kamelchukov.autoshop.model.dto.person.PersonDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Catalog {
    private Dealership dealership;
    private List<CarDto> cars;
    private List<Price> prices;
    private List<PersonDto> persons;
}
