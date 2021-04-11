package com.example.xcom.controller;

import com.example.xcom.common.ApiResponse;
import com.example.xcom.dto.cart.AddToCartDto;
import com.example.xcom.dto.cart.CartDto;
import com.example.xcom.model.Product;
import com.example.xcom.service.CartService;
import com.example.xcom.service.ProductService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthController authenticationController;

    //TODO add exceptions
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto) throws ExpiredJwtException
{
    //TODO improve this part
        int userId;
        ApiResponse response =  authenticationController.getUser().getBody();
        boolean status = response.isSuccess();
        if (status){
            userId  = Integer.parseInt(response.getMessage());
        }else{
            return new ResponseEntity<ApiResponse>(new ApiResponse(false,"User not found"),HttpStatus.FORBIDDEN);
        }

        cartService.addToCart(addToCartDto,userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems()  {
        int userId = 0 ;
        ApiResponse response =  authenticationController.getUser().getBody();
        boolean status = response.isSuccess();
        if (status){
            userId  = Integer.parseInt(response.getMessage());
        }else{

        }
        CartDto cartDto = cartService.listCartItems(userId);
        return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
    }

    @PutMapping("/update/{cartID}/{quantity}")
    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable("cartID") Long cartId, @PathVariable("quantity") int quantity){
        int userId = 0 ;
        ApiResponse response =  authenticationController.getUser().getBody();
        boolean status = response.isSuccess();
        if (status){
            userId  = Integer.parseInt(response.getMessage());
        }else{

        }

        cartService.updateCartItem(cartId,quantity);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemID) {

        int userId = 0 ;
        ApiResponse response =  authenticationController.getUser().getBody();
        boolean status = response.isSuccess();
        if (status){
            userId  = Integer.parseInt(response.getMessage());
        }else{

        }

        cartService.deleteCartItem(itemID,userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }




}
