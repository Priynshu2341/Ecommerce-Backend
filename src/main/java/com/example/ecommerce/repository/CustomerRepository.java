package com.example.ecommerce.repository;

import com.example.ecommerce.entity.customer.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<CustomerEntity,Integer> {
    Optional<CustomerEntity> findByEmail(String email);
}
