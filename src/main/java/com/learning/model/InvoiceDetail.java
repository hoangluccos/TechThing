package com.learning.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "invoicedetail")
@Getter
@Setter
public class InvoiceDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "invoice_id",nullable=false)
    private Invoices invoice_id;

    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;
    private Integer quantity;

    private Date timeorder;
    private String phone;
    private double amount;

    public InvoiceDetail() {
        super();
    }

    public InvoiceDetail(Integer id, Invoices invoice_id, Product product, Integer quantity, Date timeorder, String phone, double amount) {
        this.id = id;
        this.invoice_id = invoice_id;
        this.product = product;
        this.quantity = quantity;
        this.timeorder = timeorder;
        this.phone = phone;
        this.amount = amount;
    }
}
