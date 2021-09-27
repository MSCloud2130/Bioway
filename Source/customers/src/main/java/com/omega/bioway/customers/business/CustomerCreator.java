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
        Customer toCreate = new Customer();
        toCreate.setAge(customer.getAge());
        toCreate.setDescription(customer.getDescription());
        toCreate.setEmail(customer.getEmail());
        toCreate.setName(customer.getName());
        toCreate.setPicture(customer.getPicture());
        repository.save(toCreate);
        return toCreate;
    }

}
