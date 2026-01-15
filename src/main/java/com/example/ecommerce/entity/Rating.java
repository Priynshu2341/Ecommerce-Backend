package com.example.ecommerce.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Rating {

    private Integer stars;
    private Integer count;
}
