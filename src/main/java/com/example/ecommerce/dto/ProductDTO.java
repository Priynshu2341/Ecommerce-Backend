package com.example.ecommerce.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record ProductDTO (
        UUID id,
        String name,
        String image,
        Integer priceCents,
        RatingDTO rating,
        List<String> keywords
) {
}
