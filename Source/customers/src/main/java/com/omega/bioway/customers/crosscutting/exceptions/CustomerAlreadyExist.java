package com.omega.bioway.customers.crosscutting.exceptions;


public class CustomerAlreadyExist extends RuntimeException {
    public CustomerAlreadyExist (String message) {
        super(message);
    }
}

