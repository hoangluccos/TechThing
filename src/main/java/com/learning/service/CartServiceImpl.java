package com.learning.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.model.Cart;
import com.learning.model.CartId;
import com.learning.model.Product;
import com.learning.model.User;
import com.learning.repository.CartRepository;
import com.learning.repository.ProductRepository;
import com.learning.repository.UserRepository;
@Service
public class CartServiceImpl implements CartService{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;


    @Override
    public List<Cart> getListCartProductByUserName(String username) {
        List<Cart> cartListAll = cartRepository.findAll();
        List<Cart> cartList = new ArrayList<>();
        for(Cart cart : cartListAll) {
            if(cart.getId().getUsername().equals(username)) {
                cartList.add(cart);
            }
        }
        return cartList;
    }
    @Override
    public void deleteCartByProductIdByUserName(int productId, String username) {
        CartId cartId = new CartId();
        cartId.setUsername(username);
        cartId.setProductId(productId);
        cartRepository.deleteById(cartId);
    }
    @Override
    public void updateCart(int productId, String username, int amount) {
        CartId cartId = new CartId();
        cartId.setUsername(username);
        cartId.setProductId(productId);
        Cart cart = cartRepository.findById(cartId).get();
        cart.setQuantity(amount);
        cart.setAmountMoney(amount * cart.getProduct().getPrice());
        cartRepository.save(cart);
    }
    @Override
    public void addCart(int productId, String username) {
        Cart cart = new Cart();
        CartId cartId = new CartId();
        cartId.setUsername(username);
        cartId.setProductId(productId);
        cart.setId(cartId);
        cart.setQuantity(1);
        Product product = productRepository.findById(productId).get();
        User user = userRepository.findById(username).get();
        cart.setPrice(product.getPrice());
        cart.setProduct(product);
        cart.setUser(user);
        cart.setAmountMoney(product.getPrice() * 1);
        cartRepository.save(cart);
    }


}
