package com.omega.bioway.apigateway.service.payments;

import com.omega.bioway.apigateway.entities.payment.CreatePaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class PaymentsController {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
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
