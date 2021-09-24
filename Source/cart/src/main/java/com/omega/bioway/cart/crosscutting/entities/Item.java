package com.omega.bioway.cart.crosscutting.entities;

import java.util.UUID;

import org.springframework.data.annotation.Id;


public class Item {
    @Id
    private String id;
    private String  productId;
    private String  productUrl;
    private int quantity;
    private double unitPrice;
    
    public Item(){}

    public Item(String productId, String productUrl, int quantity , double unitPrice)
    {
        this.id = UUID.randomUUID().toString();
        this.productId = productId;
        this.productUrl = productUrl;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}

