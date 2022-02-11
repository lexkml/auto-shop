package com.kamelchukov.autoshop.model.dto.catalog;

import com.kamelchukov.autoshop.model.dto.car.FullDataOfCarResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CatalogResponse {
    private String city;
    private String name;
    private List<FullDataOfCarResponse> cars;
}
