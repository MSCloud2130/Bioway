package com.omega.bioway.purchases.business;

import com.omega.bioway.purchases.crosscutting.entities.Purchase;
import com.omega.bioway.purchases.dataaccess.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseRegisterer {

    @Autowired
    PurchaseRepository repository;

    public void execute(Purchase purchase){
        repository.save(purchase);
    }
}
