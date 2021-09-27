package com.omega.bioway.apigateway.entities.purchase;

public class PurchaseCustomer {
    private String id;
    private String resource;
    private String name;

    public PurchaseCustomer() {
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
