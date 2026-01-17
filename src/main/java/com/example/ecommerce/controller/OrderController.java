package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderDTO;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private  final CustomerRepository customerRepository;

    @GetMapping("/customer")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomer(Authentication auth) {
        String email = auth.getName();
        var customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return ResponseEntity.ok(orderService.getOrderByCustomerId(customer.getId()));

    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderByOrderId(orderId));
    }

    @PostMapping("/checkout")
    public ResponseEntity<OrderDTO> checkout(Authentication auth) {
        String email = auth.getName();
        var customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return ResponseEntity.ok(orderService.checkout(customer.getId()));
    }

}
