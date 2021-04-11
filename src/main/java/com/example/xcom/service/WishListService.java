package com.example.xcom.service;


import com.example.xcom.dto.ProductDTO;
import com.example.xcom.dto.WishListDTO;
import com.example.xcom.dto.cart.AddToCartDto;
import com.example.xcom.dto.cart.CartDto;
import com.example.xcom.dto.cart.CartItemDto;
import com.example.xcom.exceptions.AuthenticationFailException;
import com.example.xcom.exceptions.CartItemNotExistException;
import com.example.xcom.exceptions.WishListItemNotExistException;
import com.example.xcom.model.Cart;
import com.example.xcom.model.WishList;
import com.example.xcom.repository.CartRepository;
import com.example.xcom.repository.ProductRepository;
import com.example.xcom.repository.WishListRepository;
import com.example.xcom.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    public WishListService(){}

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public void addToWishlist(WishListDTO wishListDTO, long userId){
        WishList wishList = getWishListFromDTO(wishListDTO,userId);
        wishListRepository.save(wishList);
    }

    private static WishList getWishListFromDTO (WishListDTO wishListDTO, long userId) {
        WishList wishList = new WishList(wishListDTO, userId);
        return wishList;
    }

    public List<ProductDTO> getWishList(long userID){
        List<WishList> body = wishListRepository.findAllByUserIdOrderByUpdatedDateDesc(userID);
        List<ProductDTO> products = new ArrayList<>();
        for (WishList wishList : body){
            products.add(productService.getDtoFromProduct(wishList.getProduct()));
        }
        return products;
    }

    public void deleteWishListItem(long id,long userId) throws AuthenticationFailException,WishListItemNotExistException {
        if (!wishListRepository.existsById(id)){
            throw new WishListItemNotExistException(Constants.CART_ITEM_NOT_EXIST);
        }
        Optional<WishList> optionalWishList = wishListRepository.findById(id);
        if (optionalWishList.isPresent()){

            WishList wishList = optionalWishList.get();
            long originalUserId = wishList.getUserId();
            if (userId!=originalUserId){
                throw new AuthenticationFailException(Constants.NOT_AUTHORIZED);
            }

        }

        wishListRepository.deleteById(id);
    }


}


