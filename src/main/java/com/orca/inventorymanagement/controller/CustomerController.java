package com.orca.inventorymanagement.controller;

import com.orca.inventorymanagement.entity.Customer;
import com.orca.inventorymanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Display signup form
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "signup";
    }

    // Handle form submission
    @PostMapping("/signup")
    public String addCustomer(@ModelAttribute Customer customer) {
        customerService.addCustomer(customer);
        return "signup-success";
    }

    // Display success page
    @GetMapping("/signup-success")
    public String signupSuccess() {
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Handle login form submission
    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password, Model model) {
        boolean auth = customerService.authenticateCustomer(email, password);
        if (auth ==true) {
            return "dashboard"; // Redirect to dashboard on success
        } else {
            model.addAttribute("errorMessage", "Invalid email or password.");
            return "login"; // Stay on login page with error message
        }
    }

}
