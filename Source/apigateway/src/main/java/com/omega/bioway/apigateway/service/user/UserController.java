package com.omega.bioway.apigateway.service.user;

import com.omega.bioway.apigateway.service.user.entities.RegisterRequest;
import com.omega.bioway.apigateway.service.user.entities.SupplierRegisterResponse;
import com.omega.bioway.apigateway.service.user.entities.identity.CreateUserRequest;
import com.omega.bioway.apigateway.service.user.entities.supplier.CreateSupplierRequest;
import com.omega.bioway.apigateway.service.user.entities.supplier.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
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

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity supplierRegister(@RequestBody RegisterRequest request){
        ResponseEntity response=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        Supplier supplier=null;
        if(request.getType().equalsIgnoreCase("SUPPLIER")){
            CreateSupplierRequest supplierRequest=new CreateSupplierRequest(request.getEmail(),request.getName(),
            request.getAge(),request.getPicture(),request.getDescription(),request.getPhone(),request.getWebPage(),
                    request.getSocialAccounts());
            try{
                supplier=restTemplate.postForObject("http://supplier-service/supplier", new HttpEntity<>(supplierRequest), Supplier.class);
            }catch(HttpClientErrorException e){
                return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
            }
        }else if(request.getType().equalsIgnoreCase("CUSTOMER")){
            //TODO: CREATE CUSTOMER
        }else{
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        }
        if(supplier.getId()!=null){
            CreateUserRequest registerRequest = new CreateUserRequest(supplier.getId(),supplier.getEmail(),
                    request.getPassword(),request.getType());
            try{
                response=restTemplate.exchange("http://identity-service/register", HttpMethod.POST, new HttpEntity<>(registerRequest), Object.class);
            }catch(HttpClientErrorException e){
                if(request.getType().equalsIgnoreCase("SUPPLIER")){
                    restTemplate.exchange("http://supplier-service/supplier/{supplier_id}", HttpMethod.DELETE,null, Object.class,supplier.getId());
                }else if(request.getType().equalsIgnoreCase("CUSTOMER")){
                    //TODO: DELETE CUSTOMER
                }
                return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
            }
        }
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
