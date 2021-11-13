package com.omega.bioway.products.service;

import com.omega.bioway.products.business.ServiceModifier;
import com.omega.bioway.products.crosscutting.entities.ModifyServiceRequest;
import com.omega.bioway.products.crosscutting.exceptions.BadRequestException;
import com.omega.bioway.products.crosscutting.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class ModifyServicePutController {

    @Autowired
    private ServiceModifier serviceModifier;

    @PutMapping(value = "products/{productId}")
    public ResponseEntity execute(@PathVariable String productId, @RequestBody ModifyServiceRequest request){
        serviceModifier.execute(productId,request);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity handleBadRequestException(BadRequestException exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
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
