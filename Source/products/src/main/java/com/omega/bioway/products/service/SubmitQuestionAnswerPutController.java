package com.omega.bioway.products.service;

import com.omega.bioway.products.business.QuestionAnswerSetter;
import com.omega.bioway.products.crosscutting.exceptions.ProductNotFoundException;
import com.omega.bioway.products.crosscutting.exceptions.QuestionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class SubmitQuestionAnswerPutController {

    @Autowired
    private QuestionAnswerSetter questionAnswerSetter;

    @PutMapping(value = "products/{productId}/questions/{questionId}/answer", consumes = "application/json")
    public ResponseEntity execute(@PathVariable String productId, @PathVariable String questionId, @RequestBody SubmitAnswerRequest request) {
        questionAnswerSetter.execute(productId, questionId, request.getAnswerContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity handleProductNotFoundException(ProductNotFoundException exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity handleQuestionNotFoundException(QuestionNotFoundException exception){
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
