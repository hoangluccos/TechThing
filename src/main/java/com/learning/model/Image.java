package com.learning.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
//lombok generate automatic getter setter
@Getter
@Setter
@Table(name = "images")
public class Image implements Serializable{
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer image_id;
	
	@ManyToOne
	@JoinColumn(name = "product_id",nullable=false)
	private Product product;
	
	private String image_src;
	
	private String color;

	public Image(Integer image_id, Product products, String image_src, String color) {
		super();
		this.image_id = image_id;
		this.product = products;
		this.image_src = image_src;
		this.color = color;
	}
	
	public Image() {
	}
}
