package com.omega.bioway.customers.business;

import com.omega.bioway.customers.crosscutting.entities.Customer;
import com.omega.bioway.customers.crosscutting.exceptions.CustomerNotFound;
import com.omega.bioway.customers.dataaccess.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerFinder {

    @Autowired
    CustomerRepository repository;

    public Customer execute(String id){
        Optional<Customer> requestedProduct = repository.findById(id);
        if (requestedProduct.isEmpty()) {
            throw new CustomerNotFound("Customer not found");
        }
        return requestedProduct.get();
    }
}
