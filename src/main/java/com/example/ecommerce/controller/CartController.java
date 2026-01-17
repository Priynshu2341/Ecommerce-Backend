package com.example.ecommerce.controller;

import com.example.ecommerce.dto.AddToCartDTO;
import com.example.ecommerce.dto.CartDTO;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.service.CartItemService;
import com.example.ecommerce.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final CustomerRepository customerRepository;


    @GetMapping()
    public CartDTO getCart(Authentication auth) {
        String email = auth.getName();
        var customer = customerRepository.findByEmail(email)
                .orElseThrow(()-> new EntityNotFoundException("Customer not found"));
        return cartService.getCart(customer.getId());
    }


    @PostMapping("/items")
    public CartDTO addToCart(
            Authentication auth,
            @RequestParam UUID productId,
            @Valid @RequestBody AddToCartDTO request
    ) {
        String email = auth.getName();
        var customer = customerRepository.findByEmail(email)
                .orElseThrow(()-> new EntityNotFoundException("Customer not found"));
        return cartService.addToCart(customer.getId(), productId, request.quantity());
    }


    @PutMapping("//items/{productId}")
    public CartDTO updateCartItem(
            Authentication auth,
            @PathVariable UUID productId,
            @RequestParam int quantity
    ) {
        String email = auth.getName();
        var customer = customerRepository.findByEmail(email)
                .orElseThrow(()-> new EntityNotFoundException("Customer not found"));
        return cartItemService.updateCartItem(customer.getId(), productId, quantity);
    }


    @DeleteMapping("//items/{productId}")
    public CartDTO removeCartItem(
            Authentication auth,
            @PathVariable UUID productId
    ) {
        String email = auth.getName();
        var customer = customerRepository.findByEmail(email)
                .orElseThrow(()-> new EntityNotFoundException("Customer not found"));
        return cartItemService.removeCartItem(customer.getId(), productId);
    }
}
