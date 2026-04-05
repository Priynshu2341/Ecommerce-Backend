package com.example.ecommerce.controller;

import com.example.ecommerce.dto.*;
import com.example.ecommerce.entity.customer.CustomerEntity;
import com.example.ecommerce.entity.customer.Role;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.security.CustomerUserDetailService;
import com.example.ecommerce.security.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {


    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtility jwtUtil;
    private final CustomerUserDetailService userDetailsService;


    @PostMapping("/register")
    public ResponseEntity<String> Register(@RequestBody CustomerRegisterRequestDTO request) {

       var customer = CustomerEntity.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .firstName(request.firstName())
                .lastName(request.lastName())
                .role(Role.CUSTOMER)
                .build();

       customerRepository.save(customer);
       return ResponseEntity.ok("Register successfully");

    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        var user = userDetailsService.loadUserByUsername(request.email());

        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);

        return new AuthResponse(accessToken,refreshToken);
    }


    @PostMapping("/refresh")
    public ResponseEntity<AuthRefreshResponse> refreshToken(@RequestBody RefreshRequest request) {
        var token = request.refreshToken();
        if (!jwtUtil.isRefreshTokenValid(token)) {
           return ResponseEntity.status(401).build();
       }

        var username = jwtUtil.extractUsername(token);
        var user = userDetailsService.loadUserByUsername(username);
        String newAccessToken = jwtUtil.generateAccessToken(user);

       return ResponseEntity.ok(new AuthRefreshResponse(newAccessToken));


    }
}


