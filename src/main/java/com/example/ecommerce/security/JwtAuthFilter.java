package com.example.ecommerce.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtility jwtUtility;
    private final CustomerUserDetailService customerUserDetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

         String header =  request.getHeader("Authorization");

         if(header != null && header.startsWith("Bearer ")) {

             String token = header.substring(7);
             String email = jwtUtility.extractUsername(token);

             if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                 UserDetails userDetails = customerUserDetailService.loadUserByUsername(email);

                 if (!jwtUtility.isTokenExpired(token)) {
                     UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                             userDetails,
                             null,
                             userDetails.getAuthorities()
                     );
                     SecurityContextHolder.getContext().setAuthentication(auth);
                 }
             }

             }
         filterChain.doFilter(request, response);


    }
}
