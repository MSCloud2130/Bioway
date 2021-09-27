package com.omega.bioway.purchases.dataaccess;

import com.omega.bioway.purchases.crosscutting.entities.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseRepository extends MongoRepository<Purchase, String> {
}
