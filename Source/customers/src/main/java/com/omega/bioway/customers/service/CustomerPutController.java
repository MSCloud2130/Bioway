package com.omega.bioway.customers.service;

import com.omega.bioway.customers.business.CustomerCreator;
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
public class CustomerPutController {
    @Autowired
    CustomerUpdater customerUpdater;

    @PutMapping(value = "/{customerId}/", consumes = "application/json")
    public ResponseEntity execute(@RequestBody Customer customer){
        customerUpdater.execute(customer);
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
