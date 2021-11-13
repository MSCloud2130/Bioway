package com.omega.bioway.products.service;

import java.util.HashMap;

import com.omega.bioway.products.business.RaitingAdder;
import com.omega.bioway.products.crosscutting.exceptions.CalificationOutOfBondaries;
import com.omega.bioway.products.crosscutting.exceptions.ProductNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CreateRatingPostController {
    
    @Autowired
    private RaitingAdder ratingAdder;

    @PostMapping(value = "products/{productId}/ratings", consumes = "application/json")
    public ResponseEntity execute(@PathVariable String productId, @RequestBody CreateRatingRequest request){
        ratingAdder.execute(productId, request.getValue(), request.getComment(),request.getUserId(), request.getUserName(), request.getResource());
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity handleProductNotFoundException(ProductNotFoundException exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CalificationOutOfBondaries.class)
    public ResponseEntity handleCalificationOutOfBondaries(CalificationOutOfBondaries exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    static class CreateRatingRequest {

        private int value;
        private String comment;
        private String userId;
        private String userName;
        private String resource;

        public CreateRatingRequest() {
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getComment() {
            return this.comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getResource() {
            return this.resource;
        }

        public void setResource(String resource) {
            this.resource = resource;
        }
    }
}
