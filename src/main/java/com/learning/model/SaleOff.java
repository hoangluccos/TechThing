package com.learning.model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "saleoffs")
public class SaleOff implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer saleoff_id;

    @OneToMany(mappedBy = "saleoffs")
    private Set<Product> products;

    private int saleoff_percent;
    private String timestart;
    private String timeend;
    private String saleoff_name;
    
	public SaleOff(Integer saleoff_id, Set<Product> products, int saleoff_percent, String timestart, String timeend,
			String saleoff_name) {
		super();
		this.saleoff_id = saleoff_id;
		this.products = products;
		this.saleoff_percent = saleoff_percent;
		this.timestart = timestart;
		this.timeend = timeend;
		this.saleoff_name = saleoff_name;
	}

	public SaleOff() {
	}
}
