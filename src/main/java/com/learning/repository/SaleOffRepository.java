package com.learning.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learning.model.SaleOff;

@Repository
public interface SaleOffRepository extends CrudRepository<SaleOff, Integer>{
}
