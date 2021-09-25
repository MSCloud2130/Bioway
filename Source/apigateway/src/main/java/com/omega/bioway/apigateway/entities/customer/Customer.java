package com.omega.bioway.apigateway.entities.customer;

public class Customer {

    private String id;
    private String name;
    private String email;
    private int age;
    private String picture;
    private String description;
    private String password;

    public Customer() {
    }

    public Customer(String name, String email, int age, String picture, String description, String password) {

        this.name = name;
        this.email = email;
        this.age = age;
        this.picture = picture;
        this.description = description;
        this.password = password;
    }

    public Customer(String id, String name, String email, int age, String picture, String description, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.picture = picture;
        this.description = description;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
