package com.omega.bioway.apigateway.service.supplier;

import com.omega.bioway.apigateway.entities.identity.CreateUserRequest;
import com.omega.bioway.apigateway.entities.supplier.CreateSupplierRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import com.omega.bioway.apigateway.entities.supplier.ModifySupplierRequest;
import com.omega.bioway.apigateway.entities.supplier.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class SupplierController {

    private RestTemplate restTemplate;

    @Autowired
    public SupplierController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @PostMapping(value = "/supplier", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerSupplier(@RequestBody RegisterRequest request){
        ResponseEntity response;
        Supplier supplier=null;
        CreateSupplierRequest supplierRequest=new CreateSupplierRequest(request.getEmail(),request.getName(),
                request.getAge(),request.getPicture(),request.getDescription(),request.getPhone(),request.getWebPage(),
                request.getSocialAccounts());
        try{
            supplier=restTemplate.postForObject("http://supplier-service/supplier", new HttpEntity<>(supplierRequest), Supplier.class);
        }catch(HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
        CreateUserRequest registerRequest = new CreateUserRequest(supplier.getId(),supplier.getEmail(),
                request.getPassword(),request.getType());
        try{
            response=restTemplate.exchange("http://identity-service/register", HttpMethod.POST, new HttpEntity<>(registerRequest), Object.class);
        }catch(HttpClientErrorException e){
            restTemplate.exchange("http://supplier-service/supplier/{supplier_id}", HttpMethod.DELETE,null, Object.class,supplier.getId());
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }catch (IllegalStateException e){
            restTemplate.exchange("http://supplier-service/supplier/{supplier_id}", HttpMethod.DELETE,null, Object.class,supplier.getId());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @DeleteMapping(value = "/supplier/{supplier_id}")
    public ResponseEntity deleteSupplier(@PathVariable(value = "supplier_id")String supplierId){
        try{
            restTemplate.exchange("http://supplier-service/supplier/{supplier_id}", HttpMethod.DELETE, null, Object.class,supplierId);
            return restTemplate.exchange("http://identity-service/remove/{user_id}", HttpMethod.DELETE, null, Object.class,supplierId);
        }catch(HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @GetMapping(value = "/supplier")
    public ResponseEntity getAllSupplier(){
        try{
            return restTemplate.exchange("http://supplier-service/supplier", HttpMethod.GET, null, Object.class);
        }catch(HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @GetMapping(value = "/supplier/{supplier_id}")
    public ResponseEntity getSupplier(@PathVariable(value = "supplier_id")String supplierId){
        try{
            return restTemplate.exchange("http://supplier-service/supplier/{supplier_id}", HttpMethod.GET, null, Object.class,supplierId);
        }catch(HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

    @PutMapping(value = "/supplier/{supplier_id}")
    public ResponseEntity modifySupplier(@PathVariable(value = "supplier_id")String supplierId, @RequestBody ModifySupplierRequest request){
        try{
            return restTemplate.exchange("http://supplier-service/supplier/{supplier_id}", HttpMethod.PUT, new HttpEntity<>(request), Object.class, supplierId);
        }catch(HttpClientErrorException e){
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }
}
