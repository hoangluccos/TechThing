package com.learning.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
@Embeddable
public class CartId implements Serializable {
    private String username;
    private Integer productId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartId cartId = (CartId) o;
        return Objects.equals(username, cartId.username) &&
                Objects.equals(productId, cartId.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, productId);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}

