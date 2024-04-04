package com.learning.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learning.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

}
