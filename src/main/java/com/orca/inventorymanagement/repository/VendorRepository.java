package com.orca.inventorymanagement.repository;

import com.orca.inventorymanagement.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
}
