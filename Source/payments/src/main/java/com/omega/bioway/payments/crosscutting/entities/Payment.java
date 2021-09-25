package com.omega.bioway.payments.crosscutting.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "payments")
public class Payment {

    @Id
    private String id;
    private LocalDateTime date;
    private Double value;
    @DBRef
    private Card card;

    public Payment(){
        id = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
    }

    public Payment(Double value, Card card) {
        id = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.value = value;
        this.card = card;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
