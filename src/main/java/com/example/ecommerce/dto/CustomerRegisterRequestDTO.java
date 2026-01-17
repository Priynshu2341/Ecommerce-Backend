package com.example.ecommerce.dto;

public record CustomerRegisterRequestDTO(

         String email,
         String password,
         String firstName,
         String lastName
) {
}
