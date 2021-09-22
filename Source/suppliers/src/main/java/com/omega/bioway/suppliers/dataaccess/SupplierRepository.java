package com.omega.bioway.suppliers.dataaccess;

import com.omega.bioway.suppliers.crosscutting.entities.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SupplierRepository extends MongoRepository<Supplier,String> {
    Optional<Supplier> findByEmail(String email);
}
