package com.kamelchukov.autoshop.model.dto.catalog;

import com.common.model.dto.carDto.response.FullDataOfCarResponse;
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
