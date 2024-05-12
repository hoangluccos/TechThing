package com.learning.controller;

import com.learning.model.Cart;
import com.learning.model.Image;
import com.learning.model.Product;
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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping("/cart")
    public String listCart(HttpSession session, Model model) {
//        String username = (String) request.getSession().getAttribute("username");
        UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
        String username = (String)userDetails.getUsername();
        List<Cart> carts = cartService.getListCartProductByUserName(username);
        // Lấy danh sách các sản phẩm trong giỏ hàng
        List<Product> products = carts.stream()
                .map(Cart::getProduct)
                .collect(Collectors.toList());

        // Tạo map lưu trữ các hình ảnh theo id sản phẩm
        Map<Integer, List<String>> imageSrcsByProductId = products.stream()
                .collect(Collectors.toMap(
                        Product::getProduct_id,
                        p -> p.getImages().stream()
                                .map(Image::getImage_src)
                                .collect(Collectors.toList()),
                        (existing, newValue) -> existing
                ));
        model.addAttribute("carts",carts);
        model.addAttribute("imageSrcsByProductId", imageSrcsByProductId);
        return "user/cart";
    }
    @GetMapping("/cart/delete/{productId}")
    public String deleteProductInCart(@PathVariable Integer productId, HttpSession session) {
        UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
        String username = (String)userDetails.getUsername();
        cartService.deleteCartByProductIdByUserName(productId, username);
        return "redirect:/cart";
    }
    @GetMapping("/cart/update/{productId}/{amount}")
    public String updateProductInCart(@PathVariable Integer productId,@PathVariable Integer amount,HttpSession session) {
        UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
        String username = (String)userDetails.getUsername();
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
