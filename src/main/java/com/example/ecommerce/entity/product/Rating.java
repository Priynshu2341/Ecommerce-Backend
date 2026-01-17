package com.example.ecommerce.entity.product;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Rating {

    private Double stars;
    private Integer count;
}
