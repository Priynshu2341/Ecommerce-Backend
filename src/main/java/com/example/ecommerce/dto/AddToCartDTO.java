package com.example.ecommerce.dto;


import jakarta.validation.constraints.Min;

public record AddToCartDTO(
        @Min(1) int quantity
) {
}
