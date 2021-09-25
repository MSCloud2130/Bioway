package com.omega.bioway.apigateway.entities.payment;

import java.time.LocalDateTime;

public class CreatePaymentRequest {

    private Double value;
    private Card card;

    public CreatePaymentRequest(){

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
