package com.example.xcom.exceptions;

public class WishListItemNotExistException extends IllegalArgumentException {
    public WishListItemNotExistException(String msg) {
        super(msg);
    }
}
