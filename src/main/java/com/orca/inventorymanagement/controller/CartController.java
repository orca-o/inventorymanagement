package com.orca.inventorymanagement.controller;

import com.orca.inventorymanagement.entity.Cart;
import com.orca.inventorymanagement.entity.CartItem;
import com.orca.inventorymanagement.service.CartItemService;
import com.orca.inventorymanagement.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;

    public CartController(CartService cartService, CartItemService cartItemService) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addItemToCart(@RequestBody CartItem cartItem) {
        // Call the service method to add or update the item in the cart
        Cart updatedCart = cartItemService.addOrUpdateCartItem(cartItem.getCart().getCustomerId(), cartItem);
        return ResponseEntity.ok(updatedCart); // Return the updated cart
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long customerId) {
        // Get the cart for the given customerId
        Cart cart = cartService.getCartByCustomerId(customerId);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable Long cartId) {
        // Delete the cart by ID
        cartService.deleteCart(cartId);
        return ResponseEntity.ok("Cart deleted successfully");
    }

    @PostMapping("/calculate/{cartId}")
    public ResponseEntity<Cart> calculateTotal(@PathVariable Long cartId) {
        // Get the cart and calculate the total price
        Cart cart = cartService.getCartById(cartId);
        Cart updatedCart = cartService.calculateTotal(cart);
        return ResponseEntity.ok(updatedCart);
    }
}
