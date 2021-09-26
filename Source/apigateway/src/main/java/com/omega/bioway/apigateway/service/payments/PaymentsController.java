package com.omega.bioway.apigateway.service.payments;

import com.omega.bioway.apigateway.entities.payment.CreatePaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class PaymentsController {

    
    private RestTemplate restTemplate;

    @Autowired
    public PaymentsController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @PostMapping(value = "/payments", produces = "application/json")
    public ResponseEntity payServices(@RequestBody CreatePaymentRequest request){
        try {
            return restTemplate.postForEntity("http://payment-service/payments", request, Object.class);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

}
