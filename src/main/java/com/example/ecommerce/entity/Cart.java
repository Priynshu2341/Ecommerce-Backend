package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private CustomerEntity customer;


}
