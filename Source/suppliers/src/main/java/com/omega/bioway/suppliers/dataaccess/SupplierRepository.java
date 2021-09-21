package com.omega.bioway.suppliers.dataaccess;

import com.omega.bioway.suppliers.crosscutting.entities.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SupplierRepository extends MongoRepository<Supplier,String> {
}
