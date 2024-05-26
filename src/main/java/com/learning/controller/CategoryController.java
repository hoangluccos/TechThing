package com.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.model.Product;
import com.learning.model.TypeOfProducts;
import com.learning.service.CategoryService;
import com.learning.service.ProductService;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping("/admin/category")
    public String listCategory(Model model) {
        List<TypeOfProducts> category = categoryService.findAll();
        model.addAttribute("category", category);
        return "admin/category";
    }

    @GetMapping("/admin/category/list/{id}")
    public String listProductCategory(@PathVariable Integer id, Model model) {
        List<Product> product = productService.findProductsByType(id);
        model.addAttribute("products", product);
        return "admin/product";
    }
}