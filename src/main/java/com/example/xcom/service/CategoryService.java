package com.example.xcom.service;

import com.example.xcom.dto.CategoryDTO;
import com.example.xcom.model.Category;
import com.example.xcom.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    //Add authentication later
    public void addNewCategory(CategoryDTO categoryDTO){
        Category category = getCategoryFromDTO(categoryDTO);
        categoryRepository.save(category);
    }

    private Category getCategoryFromDTO(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO);
        return category;
    }

    public List<CategoryDTO> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category c : categories)
            categoryDTOList.add(getDTOfromCategory(c));
        return categoryDTOList;

    }
    public CategoryDTO getDTOfromCategory(Category category){
        CategoryDTO categoryDTO = new CategoryDTO(category);
        return categoryDTO;
    }
    //EVEN THE CREATED_AT TIME IS GETTING UPDATED> NEED TO CHANGE IT.
    public void updateCategory(long id,CategoryDTO categoryDTO){

        Category category = getCategoryFromDTO(categoryDTO);
        category.setCategoryId(id);
        category.setUpdatedDate(new Date());
        category.setImageUrl(categoryDTO.getCategoryImageURL());
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setDescription(categoryDTO.getCategoryDescription());

        categoryRepository.save(category);

    }
    public void deleteCategory(long id){
        categoryRepository.deleteById(id);
    }

    public Optional<Category> readCategory(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
