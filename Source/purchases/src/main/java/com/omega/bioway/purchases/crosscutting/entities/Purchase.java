package com.omega.bioway.purchases.crosscutting.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "purchase")
public class Purchase {
    @Id
    private String id;
    private LocalDateTime date;
    private int quantity;
    private int total;
    private PurchaseSupplier supplier;
    private PurchaseCustomer customer;
    private PurchaseProduct product;

    public void Purchase(){
        this.id = UUID.randomUUID().toString();
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public PurchaseSupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(PurchaseSupplier supplier) {
        this.supplier = supplier;
    }

    public PurchaseCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(PurchaseCustomer customer) {
        this.customer = customer;
    }

    public PurchaseProduct getProduct() {
        return product;
    }

    public void setProduct(PurchaseProduct product) {
        this.product = product;
    }
}
