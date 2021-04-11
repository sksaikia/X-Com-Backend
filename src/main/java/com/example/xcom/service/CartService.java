package com.example.xcom.service;


import com.example.xcom.controller.AuthController;
import com.example.xcom.dto.cart.AddToCartDto;
import com.example.xcom.dto.cart.CartDto;
import com.example.xcom.dto.cart.CartItemDto;
import com.example.xcom.exceptions.AuthenticationFailException;
import com.example.xcom.model.Cart;
import com.example.xcom.model.Product;
import com.example.xcom.repository.CartRepository;
import com.example.xcom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;


    public CartService(){}

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(AddToCartDto addToCartDto, long userId){
        Cart cart = getAddToCartFromDto(addToCartDto,userId);
        cartRepository.save(cart);
    }

    private Cart getAddToCartFromDto(AddToCartDto addToCartDto, long userId) {
        Cart cart = new Cart(addToCartDto, userId);
        return cart;
    }

    public CartDto listCartItems(long user_id) {
        List<Cart> cartList = cartRepository.findAllByUserIdOrderByUpdatedDateDesc(user_id);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += (cartItemDto.getProduct().getProductPrice()* cartItemDto.getQuantity());
        }
        CartDto cartDto = new CartDto(cartItems,totalCost);
        return cartDto;
    }


    public static CartItemDto getDtoFromCart(Cart cart) {
        CartItemDto cartItemDto = new CartItemDto(cart);
        return cartItemDto;
    }


    public void updateCartItem(long cartID,int quantity){
        Optional<Cart> optionalCart = cartRepository.findById(cartID);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.setQuantity(quantity);
            cart.setUpdatedDate(new Date());
            cartRepository.save(cart);
        }
    }

    public void deleteCartItem(long id,long userId) throws AuthenticationFailException {
        if (!cartRepository.existsById(id)){
            //TODO return something . Add an exception
            return ;
        }
        Optional<Cart> optionalCart = cartRepository.findById(id);
        if (optionalCart.isPresent()){

            Cart cart = optionalCart.get();
            long originalUserId = cart.getUserId();
            if (userId!=originalUserId){
                throw new AuthenticationFailException("You are not authorized to delete this item");
            }

        }

        cartRepository.deleteById(id);
    }


}


