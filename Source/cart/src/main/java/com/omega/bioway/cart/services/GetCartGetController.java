package com.omega.bioway.cart.services;

import java.util.HashMap;
import com.omega.bioway.cart.business.CartFinder;
import com.omega.bioway.cart.crosscutting.entities.Cart;
import com.omega.bioway.cart.crosscutting.exceptions.CartNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class GetCartGetController {
    @Autowired
    private CartFinder cartFinder;

    @GetMapping(value="cart/{cartId}",produces = "application/json")
    public ResponseEntity execute(@PathVariable String cartId)
    {
        Cart cart = cartFinder.execute(cartId);
        return ResponseEntity.status(HttpStatus.OK).body(cart);
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
}
