package com.example.xcom.authentication.requests;

public class UserResponse {
    private String email;
    private Long id;
    private Boolean success;

    public UserResponse(String email, Long id, Boolean success) {
        this.email = email;
        this.id = id;
        this.success = success;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}

