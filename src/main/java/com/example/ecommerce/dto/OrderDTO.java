package com.example.ecommerce.dto;

import com.example.ecommerce.entity.customer.CustomerEntity;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long orderId,
        Integer totalPriceCents,
        CustomerEntity.OrderStatus status,
        LocalDateTime createdAt,
        List<OrderItemDTO> items
) {
}
