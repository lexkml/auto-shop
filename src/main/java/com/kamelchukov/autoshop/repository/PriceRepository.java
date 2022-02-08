package com.kamelchukov.autoshop.repository;

import com.kamelchukov.autoshop.model.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {
    List<Price> findPricesByDealershipId(Long id);
}
