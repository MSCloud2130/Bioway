package com.omega.bioway.customers.crosscutting.exceptions;

public class CustomerNotFound extends RuntimeException {
    public CustomerNotFound(String message) {
        super(message);
    }
}
