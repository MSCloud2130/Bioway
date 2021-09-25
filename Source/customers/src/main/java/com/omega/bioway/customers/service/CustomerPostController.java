package com.omega.bioway.customers.service;

import com.omega.bioway.customers.business.CustomerCreator;
import com.omega.bioway.customers.crosscutting.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/customers")
public class CustomerPostController {

    @Autowired
    CustomerCreator customerCreator;

    @PostMapping(produces = "application/json")
    public ResponseEntity execute(@RequestBody Customer customer){
        Customer customerResult = customerCreator.execute(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResult);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HashMap> handleException(Exception exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
