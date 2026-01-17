package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartDTO;
import com.example.ecommerce.entity.cart.Cart;
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

public class CartService {

    private final CartUtils cartUtils;
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;


    @Transactional
    public CartDTO addToCart(Integer customerId, UUID productId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        var customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new EntityNotFoundException("Customer not found"));

        var cart = cartRepository.findByCustomerId(customer.getId())
                .orElseGet(()-> cartUtils.createCart(customer));

        var product = productRepository.findById(productId)
                .orElseThrow();

        var cartItem = cartItemRepository.findByCartAndProduct(cart, product)
                .orElseGet(()-> cartUtils.createCartItem(product, cart));

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItemRepository.save(cartItem);
        return cartMapper.toCartDTO(cart);


    }



    public CartDTO getCart(Integer customerId) {
        var customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new EntityNotFoundException("Customer not found"));

        Cart cart = cartRepository.findByCustomerId(customer.getId())
                .orElseGet(()-> cartUtils.createCart(customer));

        return  cartMapper.toCartDTO(cart);

    }


}
