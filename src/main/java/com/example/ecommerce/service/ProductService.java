package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.entity.product.Product;
import com.example.ecommerce.redis.ProductListResponse;
import com.example.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public List<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductDTO)
                .toList();


    }

    public  List<ProductDTO> findBySearch(String productName) {

        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be empty");
        }

        List<Product> product = productRepository.searchProducts(productName);

        return product.stream()
                .map(productMapper::toProductDTO)
                .toList();
    }

    @Cacheable(value = "pagedProducts2",key = "#page + '-' + #size")
    public ProductListResponse findAllPage(int  page, int size) {
        var pageable = PageRequest.of(page, size);
        var pageResult = productRepository.findAll(pageable);

        var list = pageResult.getContent()
                .stream()
                .map(productMapper::toProductDTO)
                .toList();

        return new ProductListResponse(list,page,size,pageResult.getTotalElements(),pageResult.getTotalPages(),pageResult.isFirst(),pageResult.isLast());
    }
}
