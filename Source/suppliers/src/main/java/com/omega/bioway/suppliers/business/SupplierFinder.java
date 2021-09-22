package com.omega.bioway.suppliers.business;

import com.omega.bioway.suppliers.crosscutting.entities.Supplier;
import com.omega.bioway.suppliers.crosscutting.exceptions.SupplierNotFoundException;
import com.omega.bioway.suppliers.dataaccess.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierFinder {

    private SupplierRepository repository;

    @Autowired
    public SupplierFinder(SupplierRepository repository) {
        this.repository = repository;
    }

    public Supplier execute(String supplierId){
        Optional<Supplier> supplier=repository.findById(supplierId);
        if(supplier.isEmpty()){
            throw new SupplierNotFoundException("Supplier with id: "+supplierId+" not found");
        }
        return supplier.get();
    }
}
