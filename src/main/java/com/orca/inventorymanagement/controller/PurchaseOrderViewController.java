package com.orca.inventorymanagement.controller;

import com.orca.inventorymanagement.entity.PurchaseOrder;
import com.orca.inventorymanagement.service.PurchaseOrderService;
import com.orca.inventorymanagement.service.VendorService;
import org.springframework.stereotype.Controller;


import com.orca.inventorymanagement.service.PurchaseOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PurchaseOrderViewController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderViewController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    // Show all purchase orders
    @GetMapping("/purchase-orders")
    public String showPurchaseOrders() {
        return "purchase-orders";
    }

    // Show Add Purchase Order Form
    @GetMapping("/purchase-orders/add")
    public String showAddPurchaseOrderForm(Model model) {
        // You can initialize an empty PurchaseOrder object here
        // model.addAttribute("purchaseOrder", new PurchaseOrder());
        return "add-purchase-orders";
    }

}
