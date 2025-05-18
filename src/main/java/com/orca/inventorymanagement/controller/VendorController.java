package com.orca.inventorymanagement.controller;

import com.orca.inventorymanagement.entity.Vendor;
import com.orca.inventorymanagement.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addVendor(@RequestBody Vendor vendor) {
        Vendor savedVendor = vendorService.addVendor(vendor);
        return ResponseEntity.ok("Vendor added successfully!");
    }

    @GetMapping("/get")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendors = vendorService.getAllVendors();
        return ResponseEntity.ok(vendors);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateVendor(@RequestBody Vendor vendor) {
        Vendor savedVendor = vendorService.updateVendor(vendor);
        return ResponseEntity.ok("Vendor updated successfully!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVendor(@PathVariable int id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.ok("Vendor deleted successfully!");
    }

}
