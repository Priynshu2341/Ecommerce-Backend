package com.example.ecommerce.controller;

import com.example.ecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
@RequiredArgsConstructor
public class HealthController {

    private final CustomerRepository customerRepository;

    @GetMapping()
    public ResponseEntity<String> health() {
        try {
            customerRepository.count();
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("DB ERROR");
        }
    }
}
