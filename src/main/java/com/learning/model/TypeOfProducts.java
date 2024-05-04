package com.learning.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "typeofproducts")
public class TypeOfProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer typeofproduct_id;

    @OneToMany(mappedBy = "typeofproducts")
    private Set<Product> products;

    private String typeofproduct_name;
    private String typeofproduct_desc;
    private Integer warranty_period;

    public TypeOfProducts(Integer typeofproduct_id, Set<Product> products, String typeofproduct_name, String typeofproduct_desc, Integer warranty_period) {
        this.typeofproduct_id = typeofproduct_id;
        this.products = products;
        this.typeofproduct_name = typeofproduct_name;
        this.typeofproduct_desc = typeofproduct_desc;
        this.warranty_period = warranty_period;
    }

    public TypeOfProducts() {
    }
}
