package com.omega.bioway.apigateway.service.user;

import com.omega.bioway.apigateway.entities.identity.CreateUserRequest;
import com.omega.bioway.apigateway.entities.identity.LogInRequest;
import com.omega.bioway.apigateway.entities.supplier.CreateSupplierRequest;
import com.omega.bioway.apigateway.entities.supplier.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody LogInRequest request){
        try{
            return restTemplate.exchange("http://identity-service/login", HttpMethod.POST, new HttpEntity<>(request), Object.class);
        }catch(HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }
}
