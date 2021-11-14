package com.omega.bioway.ProductSearch.crosscutting.exceptions;

public class ProductIsNotEditedException extends IllegalArgumentException{
    
    public ProductIsNotEditedException(String message) {
        super(message);
    }

}