package com.learning.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
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

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getSaleoff_id() {
		return saleoff_id;
	}

	public void setSaleoff_id(Integer saleoff_id) {
		this.saleoff_id = saleoff_id;
	}

	public Integer getTypeofproduct_id() {
		return typeofproduct_id;
	}

	public void setTypeofproduct_id(Integer typeofproduct_id) {
		this.typeofproduct_id = typeofproduct_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProduct_desc() {
		return product_desc;
	}

	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}

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
