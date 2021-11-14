package com.omega.bioway.products.crosscutting.exceptions;

public class ProductNotDeletedException extends RuntimeException {
    public ProductNotDeletedException(String message) {
        super(message);
    }
}
