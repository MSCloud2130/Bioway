package com.omega.bioway.customers.business;

import com.omega.bioway.customers.crosscutting.entities.Customer;
import com.omega.bioway.customers.dataaccess.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerCreator {

    @Autowired
    CustomerRepository repository;

    public Customer execute(Customer customer){
        repository.save(customer);
        return customer;
    }

}
