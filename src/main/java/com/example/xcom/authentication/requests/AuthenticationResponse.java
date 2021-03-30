package com.example.xcom.authentication.requests;

public class AuthenticationResponse {

    private String jwt;
    private long id;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AuthenticationResponse(String jwt, long id) {
        this.jwt = jwt;
        this.id = id;
    }
}