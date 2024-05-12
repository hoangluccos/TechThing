package com.learning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.model.Image;
import com.learning.model.Product;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByProduct(Product product);
    void deleteByProduct(Product product);
}