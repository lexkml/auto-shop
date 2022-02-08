package com.kamelchukov.autoshop.model.dto.car;

import lombok.Data;

@Data
public class CarDto {
    private Long id;
    private String model;
    private char classCar;
    private String year;
    private String color;
    private Long personId;
}
