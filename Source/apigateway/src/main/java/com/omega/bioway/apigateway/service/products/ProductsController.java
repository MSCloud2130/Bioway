package com.omega.bioway.apigateway.service.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.soap.client.SoapFaultClientException;

import com.example.consumingwebservice.wsdl.ProductSOAP;
import com.omega.bioway.apigateway.service.products.SOAP.ProductsClient;

@RestController
public class ProductsController {

    private RestTemplate restTemplate;

    @Autowired
    private ProductsClient soapClient;

    @Autowired
    public ProductsController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @GetMapping(value = "products", produces = "application/json")
    public ResponseEntity getProducts(@RequestParam(required = false) String productName, @RequestParam(required = false) String productType, @RequestParam(required = false) String supplierId){
        try{
            return new ResponseEntity<List<ProductSOAP>>(soapClient.getProducts(productName, productType, supplierId).getProducts(), HttpStatus.OK);
        }catch (SoapFaultClientException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
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
    public ResponseEntity submitAnswer(@PathVariable String productId, @PathVariable String questionId, @RequestBody SubmitAnswerRequest request) {
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
