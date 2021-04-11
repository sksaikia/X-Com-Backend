package com.example.xcom.controller;

import com.example.xcom.common.ApiResponse;
import com.example.xcom.dto.ProductDTO;
import com.example.xcom.dto.WishListDTO;
import com.example.xcom.dto.cart.AddToCartDto;
import com.example.xcom.dto.cart.CartDto;
import com.example.xcom.exceptions.AuthenticationFailException;
import com.example.xcom.exceptions.CartItemNotExistException;
import com.example.xcom.exceptions.WishListItemNotExistException;
import com.example.xcom.service.CartService;
import com.example.xcom.service.ProductService;
import com.example.xcom.service.WishListService;
import com.example.xcom.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    private WishListService wishListService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthController authenticationController;

    //TODO add exceptions
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody WishListDTO wishListDTO)
            throws AuthenticationFailException {
    //TODO improve this part
        int userId;
        ApiResponse response =  authenticationController.getUser().getBody();
        boolean status = response.isSuccess();
        if (status){
            userId  = Integer.parseInt(response.getMessage());
        }else{
            throw new AuthenticationFailException(Constants.NOT_AUTHORIZED);
        }

        wishListService.addToWishlist(wishListDTO,userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item added to the wishList"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getWishListForUser() throws AuthenticationFailException {
        int userId ;
        ApiResponse response =  authenticationController.getUser().getBody();
        boolean status = response.isSuccess();
        if (status){
            userId  = Integer.parseInt(response.getMessage());
        }else{
            throw new AuthenticationFailException(Constants.NOT_AUTHORIZED);
        }
        List<ProductDTO> productDTOS = wishListService.getWishList(userId);
        return new ResponseEntity<List<ProductDTO>>(productDTOS,HttpStatus.OK);
    }


    //TODO how to handle expired tokens
    @DeleteMapping("/delete/{wishListID}")
    public ResponseEntity<ApiResponse> deleteWishListItem(@PathVariable("wishListID") int itemID)
            throws AuthenticationFailException, WishListItemNotExistException {

        int userId;
        ApiResponse response =  authenticationController.getUser().getBody();
        boolean status = response.isSuccess();
        if (status){
            userId  = Integer.parseInt(response.getMessage());
        }else{
            throw new AuthenticationFailException(Constants.NOT_AUTHORIZED);
        }

        wishListService.deleteWishListItem(itemID,userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }


}
