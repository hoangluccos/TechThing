package com.learning.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.learning.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>, PagingAndSortingRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.typeofproducts.typeofproduct_id = :id")
    public List<Product> findByType(Integer id);

    @Query("SELECT p FROM Product p WHERE "
            + "CONCAT(p.product_name)"
            + "LIKE %?1%")
    public Page<Product> findAll(String keyword, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE "
            + "CONCAT(p.typeofproducts.typeofproduct_id)"
            + "LIKE %?1%")
    public Page<Product> findAllByCategory(String keyword, Pageable pageable);

}
