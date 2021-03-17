package com.example.xcom.controller;

import com.example.xcom.common.ApiResponse;
import com.example.xcom.dto.CategoryDTO;
import com.example.xcom.model.Category;
import com.example.xcom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addNewCategory(@RequestBody CategoryDTO categoryDTO) {

        categoryService.addNewCategory(categoryDTO);

        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added a new category"), HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {

        List<CategoryDTO> list =  categoryService.getAllCategories();

        return new ResponseEntity<List<CategoryDTO>>(list,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable("id") long id) {

        categoryService.updateCategory(id,categoryDTO);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Updated the category with id " + id), HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") long id) {

        categoryService.deleteCategory(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, " Deleted category with id " + id), HttpStatus.CREATED);

    }



}
