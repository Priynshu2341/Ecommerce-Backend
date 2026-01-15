package com.example.ecommerce.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Rating {

    private Double stars;
    private Integer count;
}
