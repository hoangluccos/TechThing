package com.learning.repository;

import com.learning.model.Cart;
import com.learning.model.CartId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CartRepository extends JpaRepository<Cart, CartId> {
    List<Cart> findAll();
}

