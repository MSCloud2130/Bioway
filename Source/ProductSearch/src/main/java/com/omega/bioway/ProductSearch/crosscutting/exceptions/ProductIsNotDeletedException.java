package com.omega.bioway.ProductSearch.crosscutting.exceptions;

public class ProductIsNotDeletedException extends IllegalArgumentException{
    
    public ProductIsNotDeletedException(String message) {
        super(message);
    }
    
}
