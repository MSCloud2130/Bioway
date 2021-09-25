package com.omega.bioway.customers.crosscutting.exceptions;

public class NoCustomersFound extends RuntimeException {
    public NoCustomersFound (String message) {
        super(message);
    }
}
