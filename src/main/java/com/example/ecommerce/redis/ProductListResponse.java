package com.example.ecommerce.redis;

import com.example.ecommerce.dto.ProductDTO;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductListResponse {

    private List<ProductDTO> products;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
}