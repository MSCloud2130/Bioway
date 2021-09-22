package com.omega.bioway.identity.crosscutting.entities;

import com.omega.bioway.identity.crosscutting.exceptions.BadRequestException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        this.validateEmail(email);
        this.email = email;
        this.validatePassword(password);
        this.password = password;
        this.type=this.validateType(type);
    }

    private String validateType(String type) {
        if(type.equalsIgnoreCase("CUSTOMER")){
            type="CUSTOMER";
        }else if(type.equalsIgnoreCase("SUPPLIER")){
            type="SUPPLIER";
        }else{
            throw new BadRequestException("Incorrect user type:"+type+". Pleas select: SUPPLIER or CUSTOMER");
        }
        return type;
    }

    private void validatePassword(String password) {
        if(password.length()<=3){
            throw new BadRequestException("Password must at least have 4 characters o numbers");
        }
        return;
    }

    private void validateEmail(String email) {
        Pattern pattern = Pattern.compile("[a-z-A-Z-0-9]+@[a-z-A-Z]+(.(edu|com|co))+");
        Matcher matcher = pattern.matcher(email);
        if(!matcher.find()) {
            throw new BadRequestException("Invalid email");
        }
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
        this.validateEmail(email);
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.validatePassword(password);
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type=this.validateType(type);
    }
}
