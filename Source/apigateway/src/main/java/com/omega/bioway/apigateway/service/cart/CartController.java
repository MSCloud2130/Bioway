package com.omega.bioway.apigateway.service.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class CartController {
    private RestTemplate restTemplate;

    @Autowired
    public CartController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @PostMapping(value = "cart", produces = "application/json")
    public ResponseEntity createCart(){
        try {
            return restTemplate.postForEntity("http://cart-service/cart/", null, Object.class, "");
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }


    @GetMapping(value="cart/{cartId}",produces = "application/json")
    public ResponseEntity getCart(@PathVariable String cartId)
    {
        try {
            return restTemplate.getForEntity("http://cart-service/cart/{cartId}", Object.class, cartId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }


    @PutMapping(value="cart/{cartId}/items", consumes = "application/json", produces = "application/json")
    public ResponseEntity AddItenCart(@PathVariable String cartId,@RequestBody AddItemRequest request)
    {
        try {
            return restTemplate.exchange("http://cart-service/cart/{cartId}/items", HttpMethod.PUT, new HttpEntity<>(request), Object.class, cartId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }


    @PatchMapping(value="cart/{cartId}/items", consumes = "application/json", produces = "application/json")
    public ResponseEntity editQuantityItemCart(@PathVariable String cartId, @RequestBody EditItemRequest request)
    {   
        try {
            return restTemplate.exchange("http://cart-service/cart/{cartId}/items", HttpMethod.PATCH, new HttpEntity<>(request), Object.class, cartId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }


    @DeleteMapping(value="cart/{cartId}/items/{productId}",produces = "application/json")
    public ResponseEntity deleteItemCart(@PathVariable String cartId, @PathVariable String productId)
    {   
        try {
            return restTemplate.exchange("http://cart-service/cart/{cartId}/items", HttpMethod.DELETE, null, Object.class, cartId,productId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    static class EditItemRequest {

        private String productId;
        private int    quantity;

        public EditItemRequest() {
        }

        public String getProductId() {
            return this.productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return this.quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

    }

    static class AddItemRequest {

        private String productId;
        private double unitPrice;
        private String productUrl;
        private int    quantity;


        public AddItemRequest() {
        }

        public String getProductId() {
            return this.productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductUrl() {
            return this.productUrl;
        }

        public void setProductUrl(String productUrl) {
            this.productUrl = productUrl;
        }


        public int getQuantity() {
            return this.quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }



        public double getUnitPrice() {
            return this.unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }
    }

    
}
