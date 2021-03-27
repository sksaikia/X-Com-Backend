package com.example.xcom.model;

import com.example.xcom.dto.CategoryDTO;
import com.example.xcom.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long productId;

    @Column(name = "product_name")
    private @NotNull String productName;

    @Column(name="product_price")
    private @NotNull double productPrice;

    @Column(name="product_description")
    private @NotNull String productDescription;

    @Column(name="updated_at")
    private @NotNull Date updatedAt;

    @Column(name="product_image_url")
    private @NotNull String productImageURL;

    @Column(name="product_is_active")
    private @NotNull Boolean productIsActive;

    @Column(name="product_stock")
    private @NotNull Integer productStock;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    public Product(ProductDTO productDTO,Category category) {
        this.setProductId(productDTO.getId());
        this.setProductName(productDTO.getProductName());
        this.setProductDescription(productDTO.getProductDescription());
        this.setProductImageURL(productDTO.getProductImageURL());
        this.setProductStock(productDTO.getProductStock());
        this.setProductPrice(productDTO.getProductPrice());
        this.setProductIsActive(true);
        this.setUpdatedAt(new Date());
        this.category = category;
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProductImageURL() {
        return productImageURL;
    }

    public void setProductImageURL(String productImageURL) {
        this.productImageURL = productImageURL;
    }

    public Boolean getProductIsActive() {
        return productIsActive;
    }

    public void setProductIsActive(Boolean productIsActive) {
        this.productIsActive = productIsActive;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product() {
    }
}

