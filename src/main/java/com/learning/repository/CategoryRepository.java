package com.learning.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learning.model.TypeOfProducts;

@Repository
public interface CategoryRepository extends CrudRepository<TypeOfProducts, Integer>{
}