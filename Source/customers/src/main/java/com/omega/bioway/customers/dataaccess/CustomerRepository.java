package com.omega.bioway.customers.dataaccess;

import com.omega.bioway.customers.crosscutting.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
