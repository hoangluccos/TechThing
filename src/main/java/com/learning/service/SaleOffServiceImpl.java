package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.model.SaleOff;
import com.learning.repository.SaleOffRepository;

@Service
public class SaleOffServiceImpl implements SaleOffService {
	
	@Autowired
	SaleOffRepository saleOffRepository;
	
	@Override
	public List<SaleOff> findAll() {
		// TODO Auto-generated method stub
		return (List<SaleOff>)saleOffRepository.findAll();
	}
	
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(List<SaleOff> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(List<Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SaleOff entity) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SaleOff> findAllById(List<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<SaleOff> findById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<SaleOff> saveAll(List<SaleOff> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaleOff save(SaleOff entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
