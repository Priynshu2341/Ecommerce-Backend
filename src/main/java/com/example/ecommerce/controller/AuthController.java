package com.example.ecommerce.controller;

import com.example.ecommerce.dto.AuthResponse;
import com.example.ecommerce.dto.CustomerRegisterRequestDTO;
import com.example.ecommerce.dto.LoginRequestDTO;
import com.example.ecommerce.entity.customer.CustomerEntity;
import com.example.ecommerce.entity.customer.Role;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.security.CustomerUserDetailService;
import com.example.ecommerce.security.JwtUtility;
import lombok.RequiredArgsConstructor;
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
    public void Register(@RequestBody CustomerRegisterRequestDTO request) {

       var customer = CustomerEntity.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .firstName(request.firstName())
                .lastName(request.lastName())
                .role(Role.CUSTOMER)
                .build();

       customerRepository.save(customer);

    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        var user = userDetailsService.loadUserByUsername(request.email());

        String token = jwtUtil.generateToken(user);

        return new AuthResponse(token);
    }
}
