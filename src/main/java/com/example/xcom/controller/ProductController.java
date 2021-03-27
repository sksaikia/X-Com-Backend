package com.example.xcom.controller;


import com.example.xcom.common.ApiResponse;
import com.example.xcom.dto.CategoryDTO;
import com.example.xcom.dto.ProductDTO;
import com.example.xcom.model.Category;
import com.example.xcom.service.CategoryService;
import com.example.xcom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addNewProduct(@RequestBody ProductDTO productDTO) {

        Optional<Category> optionalCategory = categoryService.readCategory(productDTO.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.addNewProduct(productDTO,category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added a new product"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{productID}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") long productID, @RequestBody @Valid ProductDTO productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.updateProduct(productID, productDto, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productID}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("productID") long productID){
        productService.deleteProduct(productID);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Product has been deleted"),HttpStatus.OK);
    }

    @PutMapping("/update/{productId}/{isActive}")
    public ResponseEntity<ApiResponse> updateIsActive(@PathVariable("productId") long productID,@PathVariable("isActive") boolean isActive){
        productService.changeActiveStatus(productID,isActive);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Product Active Status has been changed"),HttpStatus.OK);

    }

}
