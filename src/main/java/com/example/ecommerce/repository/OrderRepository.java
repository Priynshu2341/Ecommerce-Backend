package com.example.ecommerce.repository;

import com.example.ecommerce.entity.customer.CustomerEntity;
import com.example.ecommerce.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByCustomerId(Integer customerId);
}
