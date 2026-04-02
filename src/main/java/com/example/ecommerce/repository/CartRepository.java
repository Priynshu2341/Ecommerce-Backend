package com.example.ecommerce.repository;

import com.example.ecommerce.entity.cart.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    Optional<Cart> findByCustomerId(Integer customerId);

    Page<Cart> findByCustomerId(Integer customerId, Pageable pageable);
}
