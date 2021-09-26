package com.omega.bioway.ProductSearch.dataaccess;

import java.util.List;

import com.omega.bioway.ProductSearch.crosscutting.entities.Product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ProductRepository extends MongoRepository<Product, String>{

    public List<Product> findByNameRegex(String name);
    public List<Product> findByTypeRegex(String type);
    public List<Product> findBySupplierId(String supplierId);
    @Query("{name: {$regex: ?0}, type: {$regex: ?1}}")
    public List<Product> findByNameAndType(String name, String type);
    @Query("{name: {$regex: ?0}, supplierId: ?1}")
    public List<Product> findByNameAndSupplierId(String name, String supplierId);
    @Query("{type: {$regex: ?0}, supplierId: ?1}")
    public List<Product> findByTypeAndSupplierId(String type, String supplierId);
    @Query("{name: {$regex: ?0}, type: {$regex: ?1}, supplierId: ?2}")
    public List<Product> findByNameAndTypeAndSupplierId(String name, String type, String supplierId);
}
