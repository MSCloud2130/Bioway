package com.omega.bioway.payments.dataaccess;

import com.omega.bioway.payments.crosscutting.entities.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<Card, String> {
}
