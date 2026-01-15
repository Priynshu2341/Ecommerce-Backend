package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cart cart;
}
