package com.example.ecommerce.entity.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private Integer priceCents;

    @Embedded
    private Rating rating;

    @ElementCollection
    @CollectionTable(
            name = "product_keywords",
            joinColumns = @JoinColumn(name = "product_id")
    )
    @Column(name = "keyword")
    private List<String> keywords;
}
