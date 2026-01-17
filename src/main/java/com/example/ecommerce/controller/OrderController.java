package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderDTO;
import com.example.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{customer/customerId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomer(@PathVariable Integer customerId) {
       return ResponseEntity.ok(orderService.getOrderByCustomerId(customerId));

    }

    @GetMapping("/{order/orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderByOrderId(orderId));
    }

    @PostMapping("/checkout/{customerID}")
    public ResponseEntity<OrderDTO> checkout(@PathVariable Integer customerID) {
        return ResponseEntity.ok(orderService.checkout(customerID));
    }

}
