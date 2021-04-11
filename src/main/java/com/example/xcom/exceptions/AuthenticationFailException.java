package com.example.xcom.exceptions;

public class AuthenticationFailException extends IllegalArgumentException {
    public AuthenticationFailException(String msg) {
        super(msg);
    }
}
