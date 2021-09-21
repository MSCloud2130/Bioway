package com.omega.bioway.identity.crosscutting.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String email;
    private String password;
    private String type;

    public User() {
        this.id = id = UUID.randomUUID().toString();
    }

    public User(String email, String password, String type) {
        this.id = id = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
        this.type = type;
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
}
