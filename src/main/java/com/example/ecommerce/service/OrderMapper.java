package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderDTO;
import com.example.ecommerce.dto.OrderItemDTO;
import com.example.ecommerce.entity.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderMapper {


    public OrderDTO toOrderDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getTotalPriceCents(),
                order.getOrderStatus(),
                order.getCreatedAt(),
                order.getOrderItems().stream()
                        .map(item -> new OrderItemDTO(
                                        item.getProductId(),
                                        item.getName(),
                                        item.getImage(),
                                        item.getPriceCents(),
                                        item.getQuantity()
                                )

                        ).toList()
        );
    }

}
