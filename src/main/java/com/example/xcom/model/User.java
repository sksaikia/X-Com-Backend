package com.example.xcom.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name")
    private @NotNull String firstName;

    @Column(name = "last_name")
    private @NotNull String lastName;

    @Column(name = "password")
    private @NotNull String password;

    @Column(name = "is_active")
    private @NotNull String isActive;

    @Column(name = "updated_at")
    private @NotNull Date updatedAt;

    @Column(name = "email")
    private @NotNull String email;

    @Column(name = "contact_no")
    private @NotNull String contactNo;

    @Column(name = "profile_photo_url")
    private @NotNull String profilePhotoURL;

    @Column(name="user_role")
    private @NotNull Role role;


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(Long userId, @NotNull String firstName, @NotNull String lastName, @NotNull String password, @NotNull String isActive, @NotNull Date updatedAt, @NotNull String email, @NotNull String contactNo, @NotNull String profilePhotoURL) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.isActive = isActive;
        this.updatedAt = updatedAt;
        this.email = email;
        this.contactNo = contactNo;
        this.profilePhotoURL = profilePhotoURL;
    }
    public User(String email,String password){
        this.email = email;
        this.password = password;
    }
    public User(){

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getProfilePhotoURL() {
        return profilePhotoURL;
    }

    public void setProfilePhotoURL(String profilePhotoURL) {
        this.profilePhotoURL = profilePhotoURL;
    }
}
