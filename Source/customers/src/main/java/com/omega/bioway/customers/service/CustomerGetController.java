package com.omega.bioway.customers.service;


import com.omega.bioway.customers.business.AllCustomersFinder;
import com.omega.bioway.customers.business.CustomerFinder;
import com.omega.bioway.customers.crosscutting.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerGetController {

    @Autowired
    CustomerFinder customerFinder;

    @Autowired
    AllCustomersFinder customersFinder;

    @GetMapping(value = "products/{id}", produces = "application/json")
    public ResponseEntity execute(@PathVariable String id){
        Customer customer = customerFinder.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity executeAll(){
        List<Customer> customerList = customersFinder.execute();
        return ResponseEntity.status(HttpStatus.OK).body(customerList);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HashMap> handleException(Exception exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
