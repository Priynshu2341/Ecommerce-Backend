package com.example.ecommerce.repository;

import com.example.ecommerce.entity.customer.CustomerEntity;
import com.example.ecommerce.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
   SELECT DISTINCT o
   FROM Order o
   LEFT JOIN FETCH o.orderItems
   WHERE o.customer.id = :customerId
""")
    List<Order> findByCustomerId(@Param("customerId") Integer customerId);
}
