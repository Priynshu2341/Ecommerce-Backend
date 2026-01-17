package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartDTO;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @Transactional
    public CartDTO updateCartItem(Integer customerId, UUID productId, int quantity) {

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        var cart = cartRepository.findByCustomerId(customer.getId())
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        var product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        var cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseThrow(() -> new EntityNotFoundException("Item not in cart"));

        if (quantity == 0) {
            cartItemRepository.delete(cartItem);
            return cartMapper.toCartDTO(cart);
        }

        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);

        return cartMapper.toCartDTO(cart);
    }

    @Transactional
    public CartDTO removeCartItem(Integer customerId, UUID productId) {
        return updateCartItem(customerId, productId, 0);
    }
}
