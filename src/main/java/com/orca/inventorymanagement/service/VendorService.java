package com.orca.inventorymanagement.service;

import com.orca.inventorymanagement.entity.Vendor;
import com.orca.inventorymanagement.exceptions.CustomerNotFoundException;
import com.orca.inventorymanagement.exceptions.VendorAlreadyExistsException;
import com.orca.inventorymanagement.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    @Autowired
    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public Vendor addVendor(Vendor vendor) {
        if (vendorRepository.existsByEmail(vendor.getEmail())) {
            throw new VendorAlreadyExistsException("Vendor with email " + vendor.getEmail() + " already exists.");
        }
        return vendorRepository.save(vendor);
    }

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Vendor updateVendor(Vendor vendor) {
        if (!vendorRepository.existsById(vendor.getId())) {
            throw new VendorAlreadyExistsException("Vendor with id " + vendor.getId() + " didnt exists.");
        }
        return vendorRepository.save(vendor);
    }

    public void deleteVendor(int id) {
        if (!vendorRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer with id " + id + " not found.");
        }
        vendorRepository.deleteById(id);
    }
}
