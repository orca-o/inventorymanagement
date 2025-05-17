package com.orca.inventorymanagement.repository;

import com.orca.inventorymanagement.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
