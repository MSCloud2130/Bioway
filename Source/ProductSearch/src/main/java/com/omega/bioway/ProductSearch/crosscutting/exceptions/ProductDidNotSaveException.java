package com.omega.bioway.ProductSearch.crosscutting.exceptions;

public class ProductDidNotSaveException extends IllegalArgumentException{
    
    public ProductDidNotSaveException(String message) {
        super(message);
    }

}
