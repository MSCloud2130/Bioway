package com.omega.bioway.ProductSearch.crosscutting.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
public class Product {

    @Id
    private String id;
    private String name;
    private String type;
    private String uri;

    public Product(){}

    public Product(String nName, String nType, String nUri){
        this.name = nName;
        this.type = nType;
        this.uri = nUri;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String nId){
        this.id = nId;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String nName){
        this.id = nName;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String nType){
        this.id = nType;
    }

    public String getUri(){
        return this.uri;
    }

    public void setUri(String nUri){
        this.id = nUri;
    }
}
