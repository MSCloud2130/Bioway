package com.omega.bioway.suppliers.business;

import com.omega.bioway.suppliers.crosscutting.entities.Supplier;
import com.omega.bioway.suppliers.dataaccess.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierDeleter {

    private SupplierRepository repository;
    private SupplierFinder supplierFinder;

    @Autowired
    public SupplierDeleter(SupplierRepository repository, SupplierFinder supplierFinder) {
        this.repository = repository;
        this.supplierFinder = supplierFinder;
    }

    public void execute(String supplierId){
        Supplier supplier=supplierFinder.execute(supplierId);
        repository.delete(supplier);
    }
}
