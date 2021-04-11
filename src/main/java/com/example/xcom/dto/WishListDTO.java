package com.example.xcom.dto;

import com.example.xcom.model.WishList;

import javax.validation.constraints.NotNull;

public class WishListDTO {
    private @NotNull Long wishListID;
    private @NotNull Long userID;
    private @NotNull Long productID;

    public Long getWishListID() {
        return wishListID;
    }

    public void setWishListID(Long wishListID) {
        this.wishListID = wishListID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public WishListDTO() {
    }

    public WishListDTO(WishList wishList) {
        this.setUserID(wishList.getUserId());
        this.setProductID(wishList.getProductId());
        this.setWishListID(wishList.getWishListId());
    }

}
