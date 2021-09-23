package com.omega.bioway.payments.service;

import com.omega.bioway.payments.business.PaymentCreator;
import com.omega.bioway.payments.crosscutting.entities.Card;
import com.omega.bioway.payments.crosscutting.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequestMapping("/payment")
public class PaymentPostController {

   @Autowired
   PaymentCreator paymentCreator;

   @PostMapping (produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity execute(@RequestBody PaymentRequest payment){
        paymentCreator.execute(new Payment(payment.getDate(), payment.getValue(), payment.getCard()));
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
   }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HashMap> handleException(Exception exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    static class PaymentRequest{
        private LocalDateTime date;
        private Double value;
        private Card card;

        public PaymentRequest(){

        }



        public LocalDateTime getDate() {
            return date;
        }

        public void setDate(LocalDateTime date) {
            this.date = date;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        public Card getCard() {
            return card;
        }

        public void setCard(Card card) {
            this.card = card;
        }
    }
}
