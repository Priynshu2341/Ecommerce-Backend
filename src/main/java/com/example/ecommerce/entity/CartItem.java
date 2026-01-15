package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;

@Data
@Entity
public class CartItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
}
