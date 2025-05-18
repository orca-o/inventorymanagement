package com.orca.inventorymanagement.controller;

import com.orca.inventorymanagement.entity.Product;
import com.orca.inventorymanagement.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    // Show all products
    @GetMapping("/api/viewproducts")
    public String showProducts() {
        return "view-products-admin";
    }

}
