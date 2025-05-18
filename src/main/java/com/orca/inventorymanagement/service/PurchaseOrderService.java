package com.orca.inventorymanagement.service;

import com.orca.inventorymanagement.entity.*;
import com.orca.inventorymanagement.repository.InvoiceRepository;
import com.orca.inventorymanagement.repository.ProductRepository;
import com.orca.inventorymanagement.repository.PurchaseOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepo;

    @Autowired
    private ProductRepository productRepository;

    public PurchaseOrder createPurchaseOrder(PurchaseOrder po) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(UUID.randomUUID().toString()); // ✅ generate unique invoice number
        invoice.setDate(LocalDate.now());
        invoice.setAmount(po.getAmount());
        invoice.setStatus("unpaid");

        po.setInvoice(invoice);

        return purchaseOrderRepo.save(po);
    }

    public List<PurchaseOrder> getAllOrders() {
        return purchaseOrderRepo.findAll();
    }

    public PurchaseOrder updatePurchaseOrderStatus(Long id, String newStatus) {
        Optional<PurchaseOrder> optionalPO = purchaseOrderRepo.findById(id);

        if (optionalPO.isEmpty()) {
            throw new EntityNotFoundException("PurchaseOrder not found with id: " + id);
        }

        PurchaseOrder po = optionalPO.get();
        po.setStatus(newStatus);

        Invoice invoice = po.getInvoice();
        if (invoice != null) {
            if ("closed".equalsIgnoreCase(newStatus)) {
                invoice.setStatus("paid");

                // ✅ Only when closed, assign or create a Product
                Product product = productRepository.findByName(po.getItem()).orElse(null);
                if (product == null) {
                    product = new Product();
                    product.setName(po.getItem());
                    product.setDescription("Auto-created from Purchase Order #" + po.getId() + " for vendor " + po.getVendor());
                    product.setQuantity(po.getQuantity());
                    product.setPrice(po.getAmount());
                } else {
                    product.setQuantity(product.getQuantity() + po.getQuantity());
                }

                // Save the product to the database
                productRepository.save(product);

                // ✅ Link the product to the PurchaseOrder
                po.setProduct(product);

            } else if ("cancelled".equalsIgnoreCase(newStatus)) {
                invoice.setStatus("cancelled");
            }
        }

        // Save the updated purchase order
        return purchaseOrderRepo.save(po);
    }
}
