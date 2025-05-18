package com.orca.inventorymanagement.repository;


import com.orca.inventorymanagement.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
