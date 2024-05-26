package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.model.TypeOfProducts;

public interface CategoryService {
    void deleteAll();

    void deleteAll(List<TypeOfProducts> entities);

    void deleteAllById(List<Integer> ids);

    void delete(TypeOfProducts entity);

    void deleteById(Integer id);

    long count();

    List<TypeOfProducts> findAllById(List<Integer> ids);

    List<TypeOfProducts> findAll();

    boolean existsById(Integer id);

    Optional<TypeOfProducts> findById(Integer id);

    List<TypeOfProducts> saveAll(List<TypeOfProducts> entities);

    TypeOfProducts save(TypeOfProducts entity);
}