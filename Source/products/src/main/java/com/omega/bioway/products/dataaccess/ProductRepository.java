package com.omega.bioway.products.dataaccess;

import com.omega.bioway.products.crosscutting.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
