package com.kamelchukov.autoshop.repository;

import com.kamelchukov.autoshop.model.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {
}
