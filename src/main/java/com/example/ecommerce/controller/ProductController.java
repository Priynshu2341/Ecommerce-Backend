package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
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
}
