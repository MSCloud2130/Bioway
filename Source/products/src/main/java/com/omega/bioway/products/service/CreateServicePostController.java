package com.omega.bioway.products.service;

import com.omega.bioway.products.business.ServiceCreator;
import com.omega.bioway.products.crosscutting.entities.CreateServiceRequest;
import com.omega.bioway.products.crosscutting.exceptions.BadRequestException;
import com.omega.bioway.products.crosscutting.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CreateServicePostController {

    @Autowired
    private ServiceCreator serviceCreator;

    @PostMapping(value = "products")
    public ResponseEntity execute(@RequestBody CreateServiceRequest request){
        String productId=serviceCreator.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity handleBadRequestException(BadRequestException exception){
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
