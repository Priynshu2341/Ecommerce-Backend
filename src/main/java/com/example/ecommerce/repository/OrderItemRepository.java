package com.example.ecommerce.repository;

import com.example.ecommerce.entity.order.Order;
import com.example.ecommerce.entity.order.OrderItem;
import com.example.ecommerce.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {


}
