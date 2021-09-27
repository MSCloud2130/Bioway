package com.omega.bioway.apigateway.entities.payment;

import com.omega.bioway.apigateway.entities.purchase.Purchase;

import java.time.LocalDateTime;

public class CreatePaymentRequest {


    private Card card;
    private Purchase purchase;
    private String cartId;



    public CreatePaymentRequest(){

    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public String getCartId(){
        return cartId;
    }

    public void setCartId(String id){
        this.cartId = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

}
