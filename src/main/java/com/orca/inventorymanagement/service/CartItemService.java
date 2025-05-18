package com.orca.inventorymanagement.service;



import com.orca.inventorymanagement.entity.Cart;
import com.orca.inventorymanagement.entity.CartItem;
import com.orca.inventorymanagement.repository.CartItemRepository;
import com.orca.inventorymanagement.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public CartItemService(CartItemRepository cartItemRepository, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
    }

    public Cart addOrUpdateCartItem(Long customerId, CartItem newItem) {
        // Find the cart for the given customer
        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setCustomerId(customerId);
                    newCart.setTotal(0.0);
                    newCart.setItems(new ArrayList<>());
                    return cartRepository.save(newCart);
                });

        // Check if the item already exists in the cart based on name
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getName().equalsIgnoreCase(newItem.getName()))
                .findFirst();

        if (existingItem.isPresent()) {
            // Item exists, update quantity and price
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + newItem.getQuantity());

            // Update the cart's total price
            double newTotal = cart.getItems().stream()
                    .mapToDouble(cartItem -> cartItem.getPrice() * cartItem.getQuantity())
                    .sum();
            cart.setTotal(newTotal);

            cartItemRepository.save(item);
        } else {
            // Item doesn't exist, add it to the cart
            newItem.setCart(cart);
            cart.getItems().add(newItem);

            // Update the cart's total price
            double newTotal = cart.getItems().stream()
                    .mapToDouble(cartItem -> cartItem.getPrice() * cartItem.getQuantity())
                    .sum();
            cart.setTotal(newTotal);

            cartItemRepository.save(newItem);
        }

        return cartRepository.save(cart);
    }
}