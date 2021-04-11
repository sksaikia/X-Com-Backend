package com.example.xcom.repository;

import com.example.xcom.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findAllByUserIdOrderByUpdatedDateDesc(Long userId);

}

