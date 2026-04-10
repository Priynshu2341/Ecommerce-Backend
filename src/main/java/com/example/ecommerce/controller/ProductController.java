package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.entity.product.Product;
import com.example.ecommerce.redis.ProductListResponse;
import com.example.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public List<ProductDTO> findAll() {
        return productService.findAll();
    }

    @GetMapping("/search")
    public List<ProductDTO> findByProductName(@RequestParam String productName) {
        return productService.findBySearch(productName);
    }

    @GetMapping("/all/page")
    public ProductListResponse findAllPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size)
    {
        return  productService.findAllPage(page, size);
    }
}
