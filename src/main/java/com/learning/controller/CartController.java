package com.learning.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.learning.service.CartService;

import jakarta.servlet.http.HttpServletRequest;
@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping("/cart")
    public String listCart(HttpSession session, Model model) {
//        String username = (String) request.getSession().getAttribute("username");
        UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
        String username = (String)userDetails.getUsername();
        model.addAttribute("carts", cartService.getListCartProductByUserName(username));
        return "user/cart";
    }
    @GetMapping("/cart/delete/{productId}")
    public String deleteProductInCart(@PathVariable Integer productId, HttpSession session) {
        UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
        String username = userDetails.getUsername();
        cartService.deleteCartByProductIdByUserName(productId, username);
        return "redirect:/cart";
    }
    @GetMapping("/cart/update/{productId}/{amount}")
    public String updateProductInCart(@PathVariable Integer productId,@PathVariable Integer amount,HttpSession session) {
    	UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
        String username = userDetails.getUsername();
        cartService.updateCart(productId, username, amount);
        return "redirect:/cart";
    } 
    @GetMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable(name = "productId")Integer productId,HttpSession session) {
        UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
        String username = (String)userDetails.getUsername();
        System.out.println(userDetails.getUsername());
        cartService.addCart(productId, username);
        return "redirect:/cart";
    }
}
