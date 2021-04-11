package com.example.xcom.model;

import com.example.xcom.dto.cart.AddToCartDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Long id;

    @Column(name = "user_id")
    private @NotBlank Long userId;

    @Column(name = "product_id")
    private @NotBlank Long productId;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name="product_quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    private Product product;


    public Cart() {
    }

    public Cart(AddToCartDto addToCartDto, Long userId){
        this.userId = userId;
        this.productId = addToCartDto.getProductId();
        this.quantity = addToCartDto.getQuantity();
        this.updatedDate = new Date();
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date createdDate) {
        this.updatedDate = createdDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
