package com.omega.bioway.products.crosscutting.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Question {

    private String id;
    private String content;
    private String answer;
    private LocalDateTime date;
    private ProductCustomer author;

    public Question() {
    }

    public Question(String content, String userId, String userName) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.date = LocalDateTime.now();
        ProductCustomer author = new ProductCustomer(userId, userName);
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ProductCustomer getAuthor() {
        return author;
    }

    public void setAuthor(ProductCustomer author) {
        this.author = author;
    }
}
