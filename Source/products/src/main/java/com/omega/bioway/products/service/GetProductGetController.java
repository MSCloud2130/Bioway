package com.omega.bioway.products.service;

import com.omega.bioway.products.business.ProductFinder;
import com.omega.bioway.products.crosscutting.entities.Product;
import com.omega.bioway.products.crosscutting.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class GetProductGetController {

    @Autowired
    private ProductFinder productFinder;

    @GetMapping(value = "products/{id}", produces = "application/json")
    public ResponseEntity execute(@PathVariable String id){
        Product product = productFinder.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    //TODO: REMOVE
    @PostMapping(value = "test", produces = "application/json")
    public ResponseEntity createTestData(){
        productFinder.addTestData();
        return ResponseEntity.status(HttpStatus.OK).body(null);
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
}
