package com.orca.inventorymanagement.repository;

import com.orca.inventorymanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> { // Change Integer to Long for consistency
    Optional<Product> findById(Long id); // Use Long instead of int, so it matches your ID type

    void deleteById(Long id); // Same as above, Long for consistency

    Optional<Product> findByName(String name); // Correct return type: Optional<Product>
}
