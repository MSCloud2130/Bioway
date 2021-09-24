package com.omega.bioway.apigateway.service.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@RestController
public class ProductsController {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping(value = "products/{id}", produces = "application/json")
    public ResponseEntity getProduct(@PathVariable String id){
        try {
            return restTemplate.getForEntity("http://products-service/products/{id}", Object.class, id);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }

    }

    @PostMapping(value = "products/{productId}/questions", consumes = "application/json")
    public ResponseEntity createQuestion(@PathVariable String productId, @RequestBody CreateQuestionRequest request){
        try {
            return restTemplate.postForEntity("http://products-service/products/{productId}/questions", request, Object.class, productId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @PutMapping(value = "products/{productId}/questions/{questionId}/answer", consumes = "application/json")
    public ResponseEntity execute(@PathVariable String productId, @PathVariable String questionId, @RequestBody SubmitAnswerRequest request) {
        try {
            return restTemplate.exchange("http://products-service/products/{productId}/questions/{questionId}/answer", HttpMethod.PUT, new HttpEntity<>(request), Object.class, productId, questionId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }


    static class CreateQuestionRequest {

        private String questionContent;
        private String userId;
        private String userName;

        public CreateQuestionRequest() {
        }

        public String getQuestionContent() {
            return questionContent;
        }

        public void setQuestionContent(String questionContent) {
            this.questionContent = questionContent;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    static class SubmitAnswerRequest {

        private String answerContent;

        public SubmitAnswerRequest() {
        }

        public String getAnswerContent() {
            return answerContent;
        }

        public void setAnswerContent(String answerContent) {
            this.answerContent = answerContent;
        }
    }
}