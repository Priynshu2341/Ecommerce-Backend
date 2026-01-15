package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {

    List<CartItem> findByCart(Cart cart);

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}
