package com.omega.bioway.apigateway.entities.payment;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {

    private Double value;
    private Card card;

    public Payment(){

    }

    public Payment(Double value, Card card) {
        this.value = value;
        this.card = card;
    }


    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
