package com.orca.inventorymanagement.controller;

import com.orca.inventorymanagement.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InvoiceViewController {

    private final InvoiceService invoiceService;

    public InvoiceViewController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // Show list of invoices
    @GetMapping("/invoices")
    public String showInvoicesPage(Model model) {
        return "vendor-invoice"; // this should match invoices.html or vendor-invoice.html
    }

}
