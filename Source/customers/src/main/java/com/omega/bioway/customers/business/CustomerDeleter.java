package com.omega.bioway.customers.business;

import com.omega.bioway.customers.crosscutting.entities.Customer;
import com.omega.bioway.customers.dataaccess.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDeleter {

    @Autowired
    CustomerRepository repository;

    @Autowired CustomerFinder finder;

    public void execute(String id){
        Customer customer = finder.execute(id);
        repository.delete(customer);
    }
}
