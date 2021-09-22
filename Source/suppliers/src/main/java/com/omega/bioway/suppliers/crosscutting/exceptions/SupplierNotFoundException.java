package com.omega.bioway.suppliers.crosscutting.exceptions;

public class SupplierNotFoundException extends RuntimeException{
    public SupplierNotFoundException(String message) {
        super(message);
    }
}
