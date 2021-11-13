package com.omega.bioway.products.crosscutting.entities;

public class Rating {

    private int value;
    private String comment;
    private ProductCustomer author;

    public Rating() {
    }

    public Rating(int value,String comment,ProductCustomer author){
        this.value = value;
        this.comment = comment;
        this.author = author;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ProductCustomer getAuthor() {
        return author;
    }

    public void setAuthor(ProductCustomer author) {
        this.author = author;
    }
}
