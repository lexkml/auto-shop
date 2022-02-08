package com.kamelchukov.autoshop.model.dto.person;

import com.kamelchukov.autoshop.model.dto.car.CarDto;
import lombok.Data;

import java.util.Set;

@Data
public class PersonDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Set<CarDto> cars;
}
