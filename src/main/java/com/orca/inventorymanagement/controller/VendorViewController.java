package com.orca.inventorymanagement.controller;


import com.orca.inventorymanagement.entity.Customer;
import com.orca.inventorymanagement.entity.Vendor;
import com.orca.inventorymanagement.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VendorViewController {
    private final VendorService vendorService;

    public VendorViewController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/vendors")
    public String showSignupForm() {
        return "vendors";
    }

    // Show Add Vendor Form
    @GetMapping("/addvendor")
    public String showAddVendorForm(Model model){
        return "add-vendor";
    }

    @GetMapping("/update")
    public String showUpdateVendorForm(Model model){
        return "update-vendor";
    }

}
