package com.example.ecommerce.dto;

import java.util.List;

public record CartDTO(
        Integer cartId,
        List<CartItemDTO> items,
        Integer totalPriceCents,
        Integer cartQuantity
) {
}
