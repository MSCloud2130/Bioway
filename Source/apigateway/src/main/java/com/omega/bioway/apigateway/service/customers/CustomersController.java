package com.omega.bioway.apigateway.service.customers;

import com.omega.bioway.apigateway.entities.customer.Customer;
import com.omega.bioway.apigateway.entities.identity.CreateUserRequest;
import com.omega.bioway.apigateway.service.user.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class CustomersController {

    private RestTemplate restTemplate;

    @Autowired
    public CustomersController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @GetMapping(value = "/customers", produces = "application/json")
    public ResponseEntity getCustomers(){
        try {
            return restTemplate.exchange("http://customer-service/customers", HttpMethod.GET, null, Object.class);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @GetMapping(value = "/customers/{customer_id}", produces = "application/json")
    public ResponseEntity getCustomer(@PathVariable(value = "customer_id") String customerId){
        try {
            return restTemplate.exchange("http://customer-service/customers/{customerId}", HttpMethod.GET, null, Object.class, customerId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @PostMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCustomer(@RequestBody RegisterRequest request){
        ResponseEntity response;
        Customer customer = new Customer(request.getName(), request.getEmail(), request.getAge(),
                request.getPicture(), request.getDescription(), request.getPassword());
        Customer createdCustomer = null;
        try{
            createdCustomer = restTemplate.postForObject("http://customer-service/customers", new HttpEntity<>(customer), Customer.class);
        }catch(HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
        CreateUserRequest registerRequest = new CreateUserRequest(createdCustomer.getId(),createdCustomer.getEmail(),
                createdCustomer.getPassword(),request.getType());
        try{
            response=restTemplate.exchange("http://identity-service/register", HttpMethod.POST, new HttpEntity<>(registerRequest), Object.class);
        }catch(HttpClientErrorException e){
            restTemplate.exchange("http://customer-service/customers/{customer_id}", HttpMethod.DELETE,null, Object.class,createdCustomer.getId());
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @PutMapping(value = "/customers/{customer_id}")
    public ResponseEntity modifyCustomer(@RequestBody Customer customer, @PathVariable(value = "customer_id") String customerId){
        try {
            return restTemplate.exchange("http://customer-service/customers/{customerId}", HttpMethod.PUT, new HttpEntity<>(customer), Object.class, customerId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @DeleteMapping(value = "/customers/{customer_id}")
    public ResponseEntity deleteCustomer(@PathVariable(value = "customer_id") String customerId){
        try {
            return restTemplate.exchange("http://customer-service/customers/{customerId}", HttpMethod.DELETE, null, Object.class, customerId);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }


}
