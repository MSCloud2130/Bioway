package com.omega.bioway.apigateway.service.payments;

import com.omega.bioway.apigateway.entities.payment.CreatePaymentRequest;
import com.omega.bioway.apigateway.entities.payment.Payment;
import com.omega.bioway.apigateway.entities.purchase.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
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
        Payment payment = new Payment();
        payment.setCard(request.getCard());
        payment.setValue(request.getPurchase().getTotal());
        try {
            restTemplate.postForEntity("http://payment-service/payments", payment, Object.class);
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
        try {
            restTemplate.exchange("http://cart-service/cart/{cartId}/items", HttpMethod.DELETE, null, Object.class, request.getCartId(), request.getPurchase().getProduct().getId() );
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
        try {
            return restTemplate.exchange("http://purchase-service/purchases", HttpMethod.POST, null, Object.class, request.getPurchase());
        } catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        }
    }

}
