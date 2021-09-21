package com.omega.bioway.products.crosscutting.entities;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Faq {

    private String id;
    private String question;
    private String answer;

    public Faq() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
