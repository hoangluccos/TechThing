package com.learning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learning.model.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, Integer>{
	 @Query("SELECT p FROM Image p WHERE p.product.product_id = :id")
	 public List<Image> findByProduct(Integer id);
}
