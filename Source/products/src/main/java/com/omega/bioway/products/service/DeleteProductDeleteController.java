package com.omega.bioway.products.service;

import java.util.HashMap;

import com.omega.bioway.products.business.ProductorDeleter;
import com.omega.bioway.products.crosscutting.exceptions.ProductNotDeletedException;
import com.omega.bioway.products.crosscutting.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteProductDeleteController {

    @Autowired
    private ProductorDeleter productDeleter;

    @DeleteMapping(value = "products/{id}", produces = "application/json")
    public ResponseEntity execute(@PathVariable String id){
        productDeleter.execute(id);
		HashMap<String,String> response = new HashMap<>(){{
            put("status","done");
        }};
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity handleProductNotFoundException(ProductNotFoundException exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ProductNotDeletedException.class)
    public ResponseEntity handleProductNotDeletedException(ProductNotFoundException exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}