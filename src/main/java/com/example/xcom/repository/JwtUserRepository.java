package com.example.xcom.repository;

import com.example.xcom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JwtUserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User findByEmail(String email);

    User findUserByEmail(String email);
}