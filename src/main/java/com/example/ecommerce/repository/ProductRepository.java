package com.example.ecommerce.repository;

import com.example.ecommerce.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT DISTINCT p FROM Product p JOIN p.keywords k WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :term, '%')) " +
            "OR LOWER(k) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<Product> searchProducts(@Param("term") String term);



}
