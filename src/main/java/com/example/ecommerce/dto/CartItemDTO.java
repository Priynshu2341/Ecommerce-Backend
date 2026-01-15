package com.example.ecommerce.dto;

import java.util.UUID;

public record CartItemDTO(
        UUID productId,
        String name,
        String image,
        Integer priceCents,
        Integer quantity
) {
}
