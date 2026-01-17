package com.example.ecommerce.service;

import com.example.ecommerce.dto.CartDTO;
import com.example.ecommerce.dto.CartItemDTO;
import com.example.ecommerce.entity.cart.Cart;
import com.example.ecommerce.entity.cart.CartItem;
import com.example.ecommerce.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartMapper {

    private final CartItemRepository cartItemRepository;


    public CartDTO toCartDTO(Cart cart){

       List<CartItem> items = cartItemRepository.findByCart(cart);


       List<CartItemDTO> itemDTOS = items.stream()
               .map(item -> new CartItemDTO(
                       item.getProduct().getId(),
                       item.getProduct().getName(),
                       item.getProduct().getImage(),
                       item.getProduct().getPriceCents(),
                       item.getQuantity()
                       )
               ).toList();

       int total = itemDTOS.stream()
               .mapToInt(i-> i.priceCents() * i.quantity())
               .sum();

       return new CartDTO(cart.getId(),itemDTOS,total);
    }
}
