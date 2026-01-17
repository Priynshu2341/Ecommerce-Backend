package com.example.ecommerce.dto;

import java.util.UUID;

public record OrderItemDTO(
        UUID productID,
        String name,
        String image,
        Integer priceCents,
        Integer quantity
) {
}
