package com.omega.bioway.suppliers.business;

import com.omega.bioway.suppliers.crosscutting.entities.Supplier;
import com.omega.bioway.suppliers.dataaccess.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierModifier {

    private SupplierRepository repository;
    private SupplierFinder supplierFinder;

    @Autowired
    public SupplierModifier(SupplierRepository repository, SupplierFinder supplierFinder) {
        this.repository = repository;
        this.supplierFinder = supplierFinder;
    }

    public void execute(String supplierId,String name, int age, String picture, String description, String phone, String webPage, List<String> socialAccounts){
        Supplier supplier=supplierFinder.execute(supplierId);
        supplier.setName(name);
        supplier.setAge(age);
        supplier.setPicture(picture);
        supplier.setDescription(description);
        supplier.setPhone(phone);
        supplier.setWebPage(webPage);
        supplier.setSocialAccounts(socialAccounts);
        repository.save(supplier);
    }
}
