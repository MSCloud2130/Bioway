package com.omega.bioway.payments.dataaccess;

import com.omega.bioway.payments.crosscutting.entities.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
}
