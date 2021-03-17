package com.example.xcom.dto;

import com.example.xcom.model.Category;
import com.sun.istack.NotNull;

public class CategoryDTO {
    private Long id;
    private @NotNull String categoryName;
    private @NotNull String categoryDescription;
    private @NotNull String categoryImageURL;

    public CategoryDTO() {
    }

    public CategoryDTO(Category category) {
        this.setId(category.getCategoryId());
        this.setCategoryName(category.getCategoryName());
        this.setCategoryImageURL(category.getImageUrl());
        this.setCategoryDescription(category.getDescription());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryImageURL() {
        return categoryImageURL;
    }

    public void setCategoryImageURL(String categoryImageURL) {
        this.categoryImageURL = categoryImageURL;
    }
}
