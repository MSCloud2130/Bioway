package com.omega.bioway.cart.services;

import java.util.HashMap;

import com.omega.bioway.cart.business.CartCreate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PostCartPostController {

    @Autowired
    private CartCreate cartCreate;

    @PostMapping(value = "cart/")
    public ResponseEntity execute(){
        String cartId = cartCreate.execute();
        return ResponseEntity.status(HttpStatus.CREATED).body(cartId);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}