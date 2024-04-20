package com.learning.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	
	private Integer saleoff_id;
	
	//cho nay can phai manytoone
	private Integer typeofproduct_id;
	
	private String product_name;
	
	private Integer quantity;
	
	private int price;
	
	private String product_desc;

	public Product(Integer product_id, Integer saleoff_id, Integer typeofproduct_id, String product_name,
			Integer quantity, int price, String product_desc) {
		super();
		this.product_id = product_id;
		this.saleoff_id = saleoff_id;
		this.typeofproduct_id = typeofproduct_id;
		this.product_name = product_name;
		this.quantity = quantity;
		this.price = price;
		this.product_desc = product_desc;
	}

	public Product() {
		super();
	}
	
	
	
}
