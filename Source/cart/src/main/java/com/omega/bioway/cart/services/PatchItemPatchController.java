package com.omega.bioway.cart.services;


import java.util.HashMap;

import com.omega.bioway.cart.business.ItemQuantityEdit;
import com.omega.bioway.cart.crosscutting.exceptions.CartNotFoundException;
import com.omega.bioway.cart.crosscutting.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PatchItemPatchController {
    @Autowired
    private ItemQuantityEdit itemQuantityEdit;

    @PatchMapping(value="cart/{cartId}/items", consumes = "application/json", produces = "application/json")
    public ResponseEntity execute(@PathVariable String cartId, @RequestBody EditItemRequest request)
    {   
        itemQuantityEdit.execute(request.getProductId(), cartId, request.getQuantity());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity handleCartNotFoundException(CartNotFoundException exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity handleItemNotFoundException(ItemNotFoundException exception){
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
}
