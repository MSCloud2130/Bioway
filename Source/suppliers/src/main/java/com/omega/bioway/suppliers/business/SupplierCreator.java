package com.omega.bioway.suppliers.business;

import com.omega.bioway.suppliers.crosscutting.entities.Supplier;
import com.omega.bioway.suppliers.crosscutting.exceptions.SupplierAlreadyExistException;
import com.omega.bioway.suppliers.dataaccess.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierCreator {

    private SupplierRepository repository;

    @Autowired
    public SupplierCreator(SupplierRepository repository) {
        this.repository = repository;
    }

    public Supplier execute(String email, String name, int age, String picture, String description, String phone, String webPage, List<String> socialAccounts){
        Optional<Supplier> sup=repository.findByEmail(email);
        if(sup.isPresent()){
            throw  new SupplierAlreadyExistException("Supplier with email: "+email+" already exist");
        }
        Supplier nSup=new Supplier(email,name,age,picture,description,phone,webPage,socialAccounts);
        boolean exist=true;
        do{
            sup=repository.findById(nSup.getId());
            if(sup.isEmpty()){
                exist=false;
            }else{
                nSup.setNewId();
            }
        }while(exist);
        repository.save(nSup);
        return nSup;
    }

}
