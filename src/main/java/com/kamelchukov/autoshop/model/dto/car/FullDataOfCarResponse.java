package com.kamelchukov.autoshop.model.dto.car;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FullDataOfCarResponse {
    private Long id;
    private String model;
    private String year;
    private String color;
    private Long personId;
    private String firstName;
    private String lastName;
}
