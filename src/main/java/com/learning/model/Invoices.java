package com.learning.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "invoices")
public class Invoices implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invoice_id;
    private String receiver;
    private boolean status;
    private String address;
    public Invoices() {
        super();
    }

    public Invoices(Integer invoice_id, String receiver, boolean status, String address, User username, List<InvoiceDetail> invoiceDetails) {
        this.invoice_id = invoice_id;
        this.receiver = receiver;
        this.status = status;
        this.address = address;
        this.username = username;
        this.invoiceDetails = invoiceDetails;
    }

    @ManyToOne
    @JoinColumn(name = "username",nullable=false)
    private User username;

    @OneToMany(mappedBy = "invoice_id")
    private List<InvoiceDetail> invoiceDetails;


//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "invoicedetail", joinColumns = @JoinColumn(name = "invoice_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
//    private Set<Product> products = new HashSet<>();
}
