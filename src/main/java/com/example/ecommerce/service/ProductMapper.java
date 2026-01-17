package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.dto.RatingDTO;
import com.example.ecommerce.entity.product.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public ProductDTO toProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getImage(),
                product.getPriceCents(),
                product.getRating() == null ? null
                        : new RatingDTO(
                        product.getRating().getStars(),
                        product.getRating().getCount()
                ),
                product.getKeywords()
        );


    }
}
