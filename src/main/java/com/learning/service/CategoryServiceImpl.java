package com.learning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.model.TypeOfProducts;
import com.learning.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<TypeOfProducts> findAll() {
        // TODO Auto-generated method stub
        return (List<TypeOfProducts>)categoryRepository.findAll();
    }

    @Override
    public Optional<TypeOfProducts> findById(Integer id) {
        // TODO Auto-generated method stub
        return categoryRepository.findById(id);
    }

    @Override
    public TypeOfProducts save(TypeOfProducts entity) {
        // TODO Auto-generated method stub
        return categoryRepository.save(entity);
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll(List<TypeOfProducts> entities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAllById(List<Integer> ids) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(TypeOfProducts entity) {
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
    public List<TypeOfProducts> findAllById(List<Integer> ids) {
        // TODO Auto-generated method stub
        return null;
    }



    @Override
    public boolean existsById(Integer id) {
        // TODO Auto-generated method stub
        return false;
    }



    @Override
    public List<TypeOfProducts> saveAll(List<TypeOfProducts> entities) {
        // TODO Auto-generated method stub
        return null;
    }
}