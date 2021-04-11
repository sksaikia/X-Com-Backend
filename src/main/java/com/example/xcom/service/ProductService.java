package com.example.xcom.service;

import com.example.xcom.common.ApiResponse;
import com.example.xcom.dto.CategoryDTO;
import com.example.xcom.dto.ProductDTO;
import com.example.xcom.model.Category;
import com.example.xcom.model.Product;
import com.example.xcom.repository.CategoryRepository;
import com.example.xcom.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addNewProduct(ProductDTO productDTO,Category category){
        Product product = getProductFromDTO(productDTO,category);
        productRepository.save(product);
    }

    public void updateProduct(long productId,ProductDTO productDTO,Category category){
        Product product = getProductFromDTO(productDTO,category);
        product.setProductId(productId);
        productRepository.save(product);
    }

    private Product getProductFromDTO(ProductDTO productDTO,Category category) {
        Product product = new Product(productDTO,category);
        return product;
    }

    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }
    public void changeActiveStatus(long id,boolean isActive){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setProductIsActive(isActive);
            productRepository.save(product);
        }
    }

    //USE THIS METHOD WHEN THE ORDER IS PLACED.
    public void updateProductStock(long productId,int quantity){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setProductStock(product.getProductStock()-quantity);
            productRepository.save(product);
        }
    }

    public Product getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()){
            //TODO throw exceptions
        }
        return optionalProduct.get();
    }


}


