package com.example.xcom.controller;

import com.example.xcom.common.ApiResponse;
import com.example.xcom.dto.cart.AddToCartDto;
import com.example.xcom.dto.cart.CartDto;
import com.example.xcom.exceptions.AuthenticationFailException;
import com.example.xcom.exceptions.CartItemNotExistException;
import com.example.xcom.model.Product;
import com.example.xcom.service.CartService;
import com.example.xcom.service.ProductService;
import com.example.xcom.utils.Constants;
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
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto)
            throws AuthenticationFailException
{
    //TODO improve this part
        int userId;
        ApiResponse response =  authenticationController.getUser().getBody();
        boolean status = response.isSuccess();
        if (status){
            userId  = Integer.parseInt(response.getMessage());
        }else{
            throw new AuthenticationFailException(Constants.NOT_AUTHORIZED);
        }

        cartService.addToCart(addToCartDto,userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems() throws AuthenticationFailException {
        int userId = 0 ;
        ApiResponse response =  authenticationController.getUser().getBody();
        boolean status = response.isSuccess();
        if (status){
            userId  = Integer.parseInt(response.getMessage());
        }else{
            throw new AuthenticationFailException(Constants.NOT_AUTHORIZED);
        }
        CartDto cartDto = cartService.listCartItems(userId);
        return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
    }

    @PutMapping("/update/{cartID}/{quantity}")
    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable("cartID") Long cartId,
                                                      @PathVariable("quantity") int quantity)
            throws CartItemNotExistException,AuthenticationFailException {
        int userId = 0 ;
        ApiResponse response =  authenticationController.getUser().getBody();
        boolean status = response.isSuccess();
        if (status){
            userId  = Integer.parseInt(response.getMessage());
        }else{
            throw new AuthenticationFailException(Constants.NOT_AUTHORIZED);
        }

        cartService.updateCartItem(cartId,quantity,userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    //TODO how to handle expired tokens
    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemID)
            throws AuthenticationFailException, CartItemNotExistException {

        int userId;
        ApiResponse response =  authenticationController.getUser().getBody();
        boolean status = response.isSuccess();
        if (status){
            userId  = Integer.parseInt(response.getMessage());
        }else{
            throw new AuthenticationFailException(Constants.NOT_AUTHORIZED);
        }

        cartService.deleteCartItem(itemID,userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }




}
