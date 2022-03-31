package com.kamelchukov.autoshop.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("price")
@Data
@Builder
public class Price {
    @Id
    private Long id;

    private Long dealershipId;
    private Long carId;
    private int price;
}
