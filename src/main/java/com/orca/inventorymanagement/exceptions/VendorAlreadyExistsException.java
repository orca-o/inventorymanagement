package com.orca.inventorymanagement.exceptions;

public class VendorAlreadyExistsException extends RuntimeException {
    public VendorAlreadyExistsException(String message) {
        super(message);
    }
}
