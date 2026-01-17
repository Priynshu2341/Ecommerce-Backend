package com.example.ecommerce.dto;

import com.example.ecommerce.entity.customer.CustomerEntity;
import com.example.ecommerce.entity.order.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long orderId,
        Integer totalPriceCents,
        OrderStatus status,
        LocalDateTime createdAt,
        List<OrderItemDTO> items
) {
}
