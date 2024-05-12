package com.learning.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
//lombok generate automatic getter setter
@Getter
@Setter
@Table(name = "products")
public class Product implements Serializable {
	
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer product_id;
	
	@ManyToOne
	@JoinColumn(name = "saleoff_id",nullable=false)
	private SaleOff saleoffs;

	@ManyToOne
	@JoinColumn(name = "typeofproduct_id",nullable=false)
	private TypeOfProducts typeofproducts;
	
	private String product_name;
	
	private Integer quantity;
	
	private int price;
	
	private String product_desc;

	@OneToMany(mappedBy = "product")
	private List<Cart> carts;

	@OneToMany(mappedBy = "product")
	private List<Image> images;
	
	public Product(Integer product_id, SaleOff saleoffs, TypeOfProducts typeofproducts, String product_name,
			Integer quantity, int price, String product_desc, List<Cart> carts, List<Image> images) {
		super();
		this.product_id = product_id;
		this.saleoffs = saleoffs;
		this.typeofproducts = typeofproducts;
		this.product_name = product_name;
		this.quantity = quantity;
		this.price = price;
		this.product_desc = product_desc;
		this.carts = carts;
		this.images = images;
	}
	
	public Product() {
		super();
	}
}
