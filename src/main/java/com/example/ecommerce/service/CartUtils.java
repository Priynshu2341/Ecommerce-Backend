package com.example.ecommerce.service;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.CustomerEntity;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
public class CartUtils {

    private final CartRepository cartRepository;

    public Cart createCart(CustomerEntity customer) {
        Cart cart = new Cart();
        cart.setCustomer(customer);
        return cartRepository.save(cart);
    }

    public CartItem createCartItem(Product product, Cart cart) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setCart(cart);
        cartItem.setQuantity(0);
        return cartItem;
    }
}
