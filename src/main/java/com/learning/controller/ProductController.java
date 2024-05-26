package com.learning.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.learning.model.Image;
import com.learning.service.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.learning.model.Product;
import com.learning.model.SaleOff;
import com.learning.model.TypeOfProducts;
import com.learning.service.ProductService;
import com.learning.service.SaleOffService;
import com.learning.service.CategoryService;
import com.learning.service.ImageService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	SaleOffService saleOffService;
	//handle method to handle list product and return model and view

	@GetMapping("/admin/product/new")
	public String createProduct(Model model) {
		//Create product object to hold product from data
		Product product = new Product();
		List<TypeOfProducts> category = categoryService.findAll();
		List<SaleOff> saleoff = saleOffService.findAll();
		model.addAttribute("product", product);
		model.addAttribute("listcategories", category);
		model.addAttribute("listsaleoffs", saleoff);
		return "admin/create_product";
	}
	@Autowired
	private ImageServiceImpl imageService;

	@PostMapping("/admin/product/save_new")
	public String saveProduct(@ModelAttribute("product") Product product, @RequestParam("imageSources") String[] imageSources) {
		try {
			// Lưu thông tin sản phẩm
			productService.save(product);

			// Lưu các ảnh
			if (product.getImages() == null) {
				product.setImages(new ArrayList<>());
			}
			for (String imageSource : imageSources) {
				if (imageSource != null && !imageSource.trim().isEmpty()) {
					Image image = new Image(null, product.getProduct_id(), imageSource, null);
					imageService.save(image);
					product.getImages().add(image);
				}
			}

			// Lưu lại sản phẩm để cập nhật thông tin ảnh
			productService.save(product);
		} catch (Exception e) {
			// Không làm gì cả
		}

		return "redirect:/admin/category";
	}

	@GetMapping("/admin/product/edit/{id}")
	public String editProduct(@PathVariable Integer id, Model model) {
		Optional<Product> optionalProduct = productService.findById(id);
		List<TypeOfProducts> category = categoryService.findAll();
		List<SaleOff> saleoff = saleOffService.findAll();
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			model.addAttribute("product", product);
			model.addAttribute("listcategories", category);
			model.addAttribute("listsaleoffs", saleoff);
//			model.addAttribute("images", product.getImages());
//			model.addAttribute("imageSources", product.getImages().stream()
//					.map(Image::getImage_src)
//					.collect(Collectors.toList()));
			return "admin/edit_product";
		} else {
			return "error/404";
		}
	}

	@PostMapping("/admin/product/save_edit")
	public String updateProduct(
			@ModelAttribute("product") Product product) {
//			,@RequestParam("imageSources") String[] imageSources) {

		productService.save(product);

//		try {
//			// Lưu thông tin sản phẩm
//			productService.save(product);
//
//			// Xử lý các ảnh
//			List<Image> existingImages = imageService.getImagesByProductId(product.getProduct_id());
//			for (int i = 0; i < imageSources.length; i++) {
//				String imageSource = imageSources[i];
//				if (imageSource != null && !imageSource.trim().isEmpty()) {
//					if (i < existingImages.size()) {
//						// Cập nhật ảnh hiện có
//						existingImages.get(i).setImage_src(imageSource);
//						imageService.save(existingImages.get(i));
//					} else {
//						// Thêm ảnh mới
//						Image image = new Image(null, product.getProduct_id(), imageSource, null);
//						imageService.save(image);
//					}
//				} else if (i < existingImages.size()) {
//					// Xóa ảnh nếu URL của nó bị xóa
//					imageService.delete(existingImages.get(i));
//				}
//			}
//		} catch (Exception e) {
//			// Không làm gì cả
//		}

		return "redirect:/admin/category";
	}

	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		productService.deleteById(id);
		return "redirect:/admin/category";
	}

	@GetMapping("/product/{id}")
	public String getProductDetails(@PathVariable("id") Integer id, Model model) {
		Optional<Product> optionalProduct = productService.findById(id);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			List<Image> images = product.getImages();
			model.addAttribute("product", product);
			model.addAttribute("images", images);
			return "user/product/product-details";
		} else {
			return "error/404";
		}
	}
}