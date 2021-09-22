package com.omega.bioway.apigateway.service.user.entities;

import java.util.List;

public class SupplierRegisterResponse {
    private String id;
    private String email;
    private String password;
    private String type;
    private String name;
    private int age;
    private String picture;
    private String description;
    private String phone;
    private String webPage;
    private List<String> socialAccounts;

    public SupplierRegisterResponse() {
    }

    public SupplierRegisterResponse(String id, String email, String password, String type, String name, int age,
                                    String picture, String description, String phone, String webPage,
                                    List<String> socialAccounts) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.type = type;
        this.name = name;
        this.age = age;
        this.picture = picture;
        this.description = description;
        this.phone = phone;
        this.webPage = webPage;
        this.socialAccounts = socialAccounts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public List<String> getSocialAccounts() {
        return socialAccounts;
    }

    public void setSocialAccounts(List<String> socialAccounts) {
        this.socialAccounts = socialAccounts;
    }
}
