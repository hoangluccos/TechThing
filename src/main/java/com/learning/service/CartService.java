package com.learning.service;

import java.util.List;

import com.learning.model.Cart;

public interface CartService {
    List<Cart> getListCartProductByUserName(String username);
    void deleteCartByProductIdByUserName(int productId, String username);
    void updateCart(int productId, String username, int amount);
    void addCart(int productId, String username);
}
