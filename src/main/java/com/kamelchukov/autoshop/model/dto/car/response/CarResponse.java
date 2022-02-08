package com.kamelchukov.autoshop.model.dto.car.response;

import com.kamelchukov.autoshop.model.dto.person.response.PersonResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarResponse {
    private Long id;
    private String model;
    private char classCar;
    private String year;
    private String color;
    private int price;
    private PersonResponse person;
}
