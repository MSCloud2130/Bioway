package com.omega.bioway.customers.business;

import com.omega.bioway.customers.crosscutting.entities.Customer;
import com.omega.bioway.customers.dataaccess.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllCustomersFinder {

    @Autowired
    CustomerRepository repository;

    public List<Customer> execute(){
        List<Customer> customers = repository.findAll();
        return customers;
    }
}
