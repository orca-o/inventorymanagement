package com.orca.inventorymanagement.repository;

import com.orca.inventorymanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

}
