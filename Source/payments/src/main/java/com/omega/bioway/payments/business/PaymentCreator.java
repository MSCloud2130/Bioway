package com.omega.bioway.payments.business;

import com.omega.bioway.payments.crosscutting.entities.Payment;
import com.omega.bioway.payments.dataaccess.CardRepository;
import com.omega.bioway.payments.dataaccess.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentCreator {

    @Autowired
    PaymentRepository repository;

    @Autowired
    CardRepository cardRepository;

    public void execute(Payment payment){
        cardRepository.save(payment.getCard());
        repository.save(payment);
    }

}
