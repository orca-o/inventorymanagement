package com.orca.inventorymanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String vendorName;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    private String email;

    private String address;

    @OneToMany(mappedBy = "vendor")
    private ArrayList<PurchaseOrder> purchaseOrders;
}
