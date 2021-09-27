package com.omega.bioway.apigateway.entities.purchase;


import java.time.LocalDateTime;
import java.util.UUID;


public class Purchase {


    private LocalDateTime date;
    private int quantity;
    private double total;
    private PurchaseSupplier supplier;
    private PurchaseCustomer customer;
    private PurchaseProduct product;

    public Purchase(){
        this.date = LocalDateTime.now();
    }

    public Purchase(int quantity, double total, PurchaseSupplier supplier, PurchaseCustomer customer, PurchaseProduct product) {

        this.date = LocalDateTime.now();
        this.quantity = quantity;
        this.total = total;
        this.supplier = supplier;
        this.customer = customer;
        this.product = product;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
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
