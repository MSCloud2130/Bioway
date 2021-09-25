package com.omega.bioway.customers.crosscutting.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "customer")
public class Customer {
    @Id
    private String id;
    private String name;
    private String email;
    private int age;
    private String picture;
    private String description;

    public Customer(){
        this.id = UUID.randomUUID().toString();
    }

    public Customer(String name, String email, int age, String picture, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.age = age;
        this.picture = picture;
        this.description = description;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
