package com.example.ecommerce.controller;

import com.example.ecommerce.dto.AddToCartDTO;
import com.example.ecommerce.dto.CartDTO;
import com.example.ecommerce.service.CartItemService;
import com.example.ecommerce.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;


    @GetMapping("/{customerId}")
    public CartDTO getCart(@PathVariable Integer customerId) {
        return cartService.getCart(customerId);
    }


    @PostMapping("/{customerId}/items")
    public CartDTO addToCart(
            @PathVariable Integer customerId,
            @RequestParam UUID productId,
            @Valid @RequestBody AddToCartDTO request
    ) {
        return cartService.addToCart(customerId, productId, request.quantity());
    }


    @PutMapping("/{customerId}/items/{productId}")
    public CartDTO updateCartItem(
            @PathVariable Integer customerId,
            @PathVariable UUID productId,
            @RequestParam int quantity
    ) {
        return cartItemService.updateCartItem(customerId, productId, quantity);
    }


    @DeleteMapping("/{customerId}/items/{productId}")
    public CartDTO removeCartItem(
            @PathVariable Integer customerId,
            @PathVariable UUID productId
    ) {
        return cartItemService.removeCartItem(customerId, productId);
    }
}
