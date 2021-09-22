package com.omega.bioway.suppliers.business;

import com.omega.bioway.suppliers.crosscutting.entities.Supplier;
import com.omega.bioway.suppliers.dataaccess.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AllSuppliersObtainer {

    private SupplierRepository repository;

    @Autowired
    public AllSuppliersObtainer(SupplierRepository repository) {
        this.repository = repository;
    }

    public List<Supplier> execute(){
        List<Supplier> suppliers=repository.findAll();
        if(suppliers==null){
            suppliers=new ArrayList<>();
        }
        return suppliers;
    }
}
