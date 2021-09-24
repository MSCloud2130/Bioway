package com.omega.bioway.ProductSearch.dataaccess;

import java.util.List;

import com.omega.bioway.ProductSearch.crosscutting.entities.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String>{
    public List<Product> findByNameRegex(String name);
    public List<Product> findByTypeRegex(String type);
}
