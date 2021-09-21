package com.omega.bioway.products.crosscutting.entities;

public class ProductCustomer {

    private String id;
    private String name;
    private String resource;

    public ProductCustomer() {
    }

    public ProductCustomer(String id, String name) {
        this.id = id;
        this.name = name;
        this.resource = "http://localhost:8080/customers/" + id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
