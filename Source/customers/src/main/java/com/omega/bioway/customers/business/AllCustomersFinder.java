package com.omega.bioway.customers.business;

import com.omega.bioway.customers.crosscutting.entities.Customer;
import com.omega.bioway.customers.crosscutting.exceptions.NoCustomersFound;
import com.omega.bioway.customers.dataaccess.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AllCustomersFinder {

    @Autowired
    CustomerRepository repository;

    public List<Customer> execute(){
        List<Customer> customers = repository.findAll();
        if(customers.isEmpty()){
            throw new NoCustomersFound("There are 0 customers registered");
        }
        return customers;
    }
}
