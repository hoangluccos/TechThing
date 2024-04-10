package com.learning.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.learning.model.Product;
import com.learning.service.ProductService;


@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	//handle method to handle list product and return model and view
	@GetMapping("/product")
	public String listProducts(Model model) {
		model.addAttribute("products", productService.findAll());
		return "admin/product";
	}
	
	@GetMapping("/product/new")
	public String createProduct(Model model) {
		//Create product object to hold product from data
		Product product = new Product();
		model.addAttribute("product", product);
		return "admin/create_product";
	}

	@PostMapping("/products")
	public String saveProduct(@ModelAttribute("product") Product entity) {
		productService.save(entity);
		return "redirect:/product";
	}
	
	@GetMapping("/product/edit/{id}")
	public String editStudent(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.findById(id));
		return "admin/edit_product";
	}
	
	@PostMapping("/products/{id}")
	public String updateStudent(@PathVariable Integer id,
			@ModelAttribute("product") Product product) {
			Optional<Product> existingProductOptional = productService.findById(id);
	        Product existingProduct = existingProductOptional.get();
	        existingProduct.setProduct_id(product.getProduct_id());
	        existingProduct.setSaleoff_id(product.getSaleoff_id());
	        existingProduct.setTypeofproduct_id(product.getTypeofproduct_id());
	        existingProduct.setProduct_name(product.getProduct_name());
	        existingProduct.setQuantity(product.getQuantity());
	        existingProduct.setPrice(product.getPrice());
	        existingProduct.setProduct_desc(product.getProduct_desc());
	        
	        productService.save(existingProduct); // Lưu thông tin sản phẩm đã cập nhật
	        return "redirect:/product"; // Chuyển hướng về trang danh sách sản phẩm	
	}
	
	@GetMapping("/product/{id}")
	public String deleteProduct(@PathVariable int id) {
		productService.deleteById(id);
		return "redirect:/product";
	}
}
