package com.omega.bioway.customers.service;

import com.omega.bioway.customers.business.CustomerDeleter;
import com.omega.bioway.customers.business.CustomerUpdater;
import com.omega.bioway.customers.crosscutting.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/customer")
public class CustomerDeleteController {

    @Autowired
    CustomerDeleter customerDeleter;

    @DeleteMapping(value = "/{customerId}/", consumes = "application/json")
    public ResponseEntity execute(@PathVariable String customerId){
        customerDeleter.execute(customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HashMap> handleException(Exception exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
