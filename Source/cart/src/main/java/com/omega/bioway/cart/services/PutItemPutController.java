package com.omega.bioway.cart.services;

import java.util.HashMap;
import com.omega.bioway.cart.business.ItemAdder;
import com.omega.bioway.cart.crosscutting.exceptions.CartNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PutItemPutController {
    @Autowired
    private ItemAdder itemAdder;

    @PutMapping(value="cart/{cartId}/items", consumes = "application/json", produces = "application/json")
    public ResponseEntity execute(@PathVariable String cartId,@RequestBody AddItemRequest request)
    {
        itemAdder.execute(request.productId, request.unitPrice, cartId, request.productUrl, request.quantity);
        
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity handleCartNotFoundException(CartNotFoundException exception){
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
