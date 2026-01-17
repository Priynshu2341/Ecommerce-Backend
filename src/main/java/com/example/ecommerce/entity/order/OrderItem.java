package com.example.ecommerce.entity.order;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID productId;
    private String name;
    private String image;
    private Integer priceCents;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;
}
