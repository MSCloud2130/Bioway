package com.omega.bioway.payments.crosscutting.exceptions;

public class InvalidCard  extends RuntimeException {
    public InvalidCard(String message) {
        super(message);
    }
}
