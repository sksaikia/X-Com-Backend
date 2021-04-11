package com.example.xcom.dto.cart;


import com.example.xcom.model.Cart;
import com.example.xcom.model.Product;

import javax.validation.constraints.NotNull;

public class CartItemDto {
    private Long id;
    private @NotNull Long userId;
    private @NotNull Integer quantity;
    private @NotNull Product product;

    public CartItemDto() {
    }

    public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setUserId(cart.getUserId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", quantity=" + quantity +
                ", productName=" + product.getProductName() +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
