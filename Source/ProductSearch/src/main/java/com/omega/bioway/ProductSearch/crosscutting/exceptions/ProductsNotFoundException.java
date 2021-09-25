package com.omega.bioway.ProductSearch.crosscutting.exceptions;

public class ProductsNotFoundException extends RuntimeException {
    
    public ProductsNotFoundException(String message) {
        super(message);
    }

}
