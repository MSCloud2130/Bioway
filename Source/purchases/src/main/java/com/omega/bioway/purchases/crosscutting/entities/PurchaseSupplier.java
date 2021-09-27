package com.omega.bioway.purchases.crosscutting.entities;



public class PurchaseSupplier {
    private String id;
    private String resource;
    private String name;

    public PurchaseSupplier() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
