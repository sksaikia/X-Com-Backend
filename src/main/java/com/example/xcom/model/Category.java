package com.example.xcom.model;


import com.example.xcom.dto.CategoryDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private @NotBlank String categoryName;

    @Column(name="category_image_url")
    private @NotBlank String imageUrl;

    @Column(name="category_description")
    private @NotBlank String description;

    @Column(name = "created_at")
    private Date createdDate;

    @Column(name = "updated_at")
    private Date updatedDate;

    public Category() {
    }

    public Category(CategoryDTO categoryDTO) {
        this.setCategoryId(categoryDTO.getId());
        this.setCategoryName(categoryDTO.getCategoryName());
        this.setDescription(categoryDTO.getCategoryDescription());
        this.setImageUrl(categoryDTO.getCategoryImageURL());
        this.setCreatedDate(new Date());
        this.setUpdatedDate(new Date());
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
