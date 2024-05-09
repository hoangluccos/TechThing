package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.model.Product;
import org.springframework.data.domain.Page;

public interface ProductService {

	void deleteAll();

	void deleteAll(List<Product> entities);

	void deleteAllById(List<Integer> ids);

	void delete(Product entity);

	void deleteById(Integer id);

	long count();

	List<Product> findAllById(List<Integer> ids);

	List<Product> findAll();

	boolean existsById(Integer id);

	Optional<Product> findById(Integer id);

	List<Product> saveAll(List<Product> entities);

	Product save(Product entity);

	List<Product> findProductsByType(Integer id);
	Page<Product> listAll(int pageNum, String sortField, String sortDir, String keyword);
	Page<Product> listAllByCategory(int pageNum, String sortField, String sortDir, String keyword);
}
