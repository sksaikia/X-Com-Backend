package com.example.xcom.dto;

import com.example.xcom.model.Category;
import com.example.xcom.model.Product;

public class ProductDTO {
    private Long id;
    private String productName;
    private Double productPrice;
    private String productDescription;
    private Boolean productIsActive;
    private Integer productStock;
    private String productImageURL;
    private Long categoryId;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.setId(product.getProductId());
        this.setProductName(product.getProductName());
        this.setProductPrice(product.getProductPrice());
        this.setProductDescription(product.getProductDescription());
        this.setProductIsActive(product.getProductIsActive());
        this.setProductStock(product.getProductStock());
        this.setProductImageURL(product.getProductImageURL());
        this.setCategoryId(product.getCategory().getCategoryId());
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
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

    public String getProductImageURL() {
        return productImageURL;
    }

    public void setProductImageURL(String productImageURL) {
        this.productImageURL = productImageURL;
    }
}
