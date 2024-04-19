package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	@GetMapping("/user/cart")
	public String listCart(HttpServletRequest request, Model model) {
		String username = (String) request.getSession().getAttribute("username");
		model.addAttribute("carts", cartService.getListCartProductByUserName(username));
		return "user/cart";
	}
	@GetMapping("/user/cart/delete/{productId}")
	public String deleteProductInCart(@PathVariable Integer productId,HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("username");
		cartService.deleteCartByProductIdByUserName(productId, username);
		return "redirect:/user/cart";
	}
	@GetMapping("/user/cart/update/{productId}/{amount}")
	public String updateProductInCart(@PathVariable Integer productId,@PathVariable Integer amount,HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("username");
		cartService.updateCart(productId, username, amount);
		return "redirect:/user/cart";
	}
	@GetMapping("/user/cart/add/{productId}")
	public String addToCart(@PathVariable Integer productId,HttpServletRequest request) {
		String username = (String) request.getSession().getAttribute("username");
		cartService.addCart(productId, username);
		return "redirect:/user/cart";
	}
}
