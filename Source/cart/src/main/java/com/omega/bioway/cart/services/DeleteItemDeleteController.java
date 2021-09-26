package com.omega.bioway.cart.services;

import java.util.HashMap;

import com.omega.bioway.cart.business.ItemRemover;
import com.omega.bioway.cart.crosscutting.exceptions.CartNotFoundException;
import com.omega.bioway.cart.crosscutting.exceptions.ItemNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DeleteItemDeleteController {
    @Autowired
    private ItemRemover itemRemover;

    @DeleteMapping(value="cart/{cartId}/items/{productId}",produces = "application/json")
    public ResponseEntity execute(@PathVariable String cartId, @PathVariable String productId)
    {   
        itemRemover.execute(productId, cartId);
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
}
