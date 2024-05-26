package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.model.SaleOff;


public interface SaleOffService {
	void deleteAll();

	void deleteAll(List<SaleOff> entities);

	void deleteAllById(List<Integer> ids);

	void delete(SaleOff entity);

	void deleteById(Integer id);

	long count();

	List<SaleOff> findAllById(List<Integer> ids);

	List<SaleOff> findAll();

	boolean existsById(Integer id);

	Optional<SaleOff> findById(Integer id);

	List<SaleOff> saveAll(List<SaleOff> entities);

	SaleOff save(SaleOff entity);
}
