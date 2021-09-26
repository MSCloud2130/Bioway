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
    public ResponseEntity execute(@RequestBody Payment payment){
        paymentCreator.execute(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
   }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HashMap> handleException(Exception exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }


}
