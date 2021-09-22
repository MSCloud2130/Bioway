package com.omega.bioway.suppliers.crosscutting.exceptions;

public class SupplierAlreadyExistException extends RuntimeException{
    public SupplierAlreadyExistException(String message) {
        super(message);
    }
}
