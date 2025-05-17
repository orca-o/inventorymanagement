package com.orca.inventorymanagement.service;


import com.orca.inventorymanagement.entity.Customer;
import com.orca.inventorymanagement.exceptions.CustomerAlreadyExistsException;
import com.orca.inventorymanagement.exceptions.CustomerNotFoundException;
import com.orca.inventorymanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CustomerAlreadyExistsException("Customer with Email " + customer.getEmail() + " already exists.");
        }
        return customerRepository.save(customer);
    }


    public boolean authenticateCustomer(String email, String password) {
        if (customerRepository.existsByEmailAndPassword(email, password)) {
            return true;
        }
        return false;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}