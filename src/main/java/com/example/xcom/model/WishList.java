package com.example.xcom.model;


import com.example.xcom.dto.WishListDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "wishlist")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="wishlist_id")
    private Long wishListId;

    @Column(name = "user_id")
    private @NotBlank Long userId;

    @Column(name = "product_id")
    private @NotBlank Long productId;

    @Column(name = "updated_date")
    private Date updatedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    private Product product;

    public WishList() {
    }


    public WishList(WishListDTO wishListDTO, Long userId) {
        this.userId = userId;
        this.productId=wishListDTO.getProductID();
        this.updatedDate = new Date();
    }

    public Long getWishListId() {
        return wishListId;
    }

    public void setWishListId(Long wishListId) {
        this.wishListId = wishListId;
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

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
