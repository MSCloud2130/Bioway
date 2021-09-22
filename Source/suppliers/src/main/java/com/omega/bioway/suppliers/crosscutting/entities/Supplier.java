package com.omega.bioway.suppliers.crosscutting.entities;

import com.omega.bioway.suppliers.crosscutting.exceptions.BadRequestException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Document(collection = "supplier")
public class Supplier {

    @Id
    private String id;
    private String email;
    private String name;
    private int age;
    private String picture;
    private String description;
    private String phone;
    private String webPage;
    private List<String> socialAccounts;

    public Supplier() {
        this.id = id = UUID.randomUUID().toString();
    }

    public Supplier(String email, String name, int age, String picture, String description, String phone, String webPage, List<String> socialAccounts) {
        this.id = UUID.randomUUID().toString();
        this.validateEmail(email);
        this.email = email;
        this.name = name;
        this.validateAge(age);
        this.age = age;
        this.picture = picture;
        this.description = description;
        this.phone = phone;
        this.webPage = webPage;
        if(socialAccounts==null){
            socialAccounts=new ArrayList<>();
        }
        this.socialAccounts = socialAccounts;
    }

    private void validateAge(int age) {
        if(age<18 || age >150){
            throw new BadRequestException("Invalid age");
        }
    }

    private void validateEmail(String email) {
        Pattern pattern = Pattern.compile("[a-z-A-Z-0-9]+@[a-z-A-Z]+(.(edu|com|co))+");
        Matcher matcher = pattern.matcher(email);
        if(!matcher.find()) {
            throw new BadRequestException("Invalid email");
        }
    }

    private void validateId(String id) {
        try {
            UUID.fromString(id);
        }
        catch (Exception e){
            throw new BadRequestException("Invalid UUID");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.validateId(id);
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.validateEmail(email);
        this.email = email;
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
        this.validateAge(age);
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
        if(socialAccounts==null){
            socialAccounts=new ArrayList<>();
        }
        this.socialAccounts = socialAccounts;
    }

    public HashMap<String, String> getAllData() {
        HashMap<String,String> data = new HashMap<String,String>(){{
            put("id",id);
            put("email",email);
            put("name",name);
            put("age",Integer.toString(age));
            put("picture",picture);
            put("description",description);
            put("phone",phone);
            put("webPage",webPage);
            if(socialAccounts!=null){
                for (int i=0;i<socialAccounts.size();i++) {
                    put("SocialAccount"+i,socialAccounts.get(i));
                }
            }
        }};
        return data;
    }
}
