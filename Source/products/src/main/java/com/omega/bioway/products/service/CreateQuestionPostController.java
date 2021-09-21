package com.omega.bioway.products.service;

import com.omega.bioway.products.business.ProductQuestionAdder;
import com.omega.bioway.products.crosscutting.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class CreateQuestionPostController {

    @Autowired
    private ProductQuestionAdder questionAdder;

    @PostMapping(value = "products/{productId}/questions", consumes = "application/json")
    public ResponseEntity execute(@PathVariable String productId, @RequestBody CreateQuestionRequest request){
        questionAdder.execute(productId, request.getQuestionContent(), request.getUserId(), request.getUserName());
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity handleProductNotFoundException(ProductNotFoundException exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
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
}
