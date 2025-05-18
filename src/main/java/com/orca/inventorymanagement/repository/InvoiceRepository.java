package com.orca.inventorymanagement.repository;

import com.orca.inventorymanagement.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Optional<Invoice> findById(Long invoiceId);

}
