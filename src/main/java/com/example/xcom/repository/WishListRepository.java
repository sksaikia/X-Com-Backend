package com.example.xcom.repository;

import com.example.xcom.model.Cart;
import com.example.xcom.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    List<WishList> findAllByUserIdOrderByUpdatedDateDesc(Long userId);

}
