package com.omega.bioway.customers.business;

import com.omega.bioway.customers.crosscutting.entities.Customer;
import com.omega.bioway.customers.dataaccess.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerUpdater {

    @Autowired
    CustomerRepository repository;

    @Autowired
    CustomerFinder finder;

    public void execute(Customer customer){
        finder.execute(customer.getId());
        repository.save(customer);
    }
}
