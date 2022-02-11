package com.kamelchukov.autoshop.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Data
@Builder
@Table("dealership")
public class Dealership {
    @Id
    private Long id;

    private String name;
    private String city;

    @MappedCollection(idColumn = "dealership_id")
    private Set<Price> prices;
}
