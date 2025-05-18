package com.orca.inventorymanagement.service;

import com.orca.inventorymanagement.entity.Invoice;
import com.orca.inventorymanagement.repository.InvoiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    /**
     * Get a list of all invoices
     */
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice updateInvoiceStatus(Long invoiceId, String newStatus) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);

        if (optionalInvoice.isPresent()) {
            Invoice invoice = optionalInvoice.get();
            invoice.setStatus(newStatus);
            return invoiceRepository.save(invoice);
        } else {
            throw new EntityNotFoundException("Invoice not found with ID: " + invoiceId);
        }
    }
}
