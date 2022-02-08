package com.kamelchukov.autoshop.repository;

import com.kamelchukov.autoshop.model.Dealership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealershipRepository extends CrudRepository<Dealership, Long> {
}
