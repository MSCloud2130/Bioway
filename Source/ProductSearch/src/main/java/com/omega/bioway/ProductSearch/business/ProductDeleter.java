package com.omega.bioway.ProductSearch.business;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.omega.bioway.ProductSearch.crosscutting.entities.Product;
import com.omega.bioway.ProductSearch.crosscutting.exceptions.ProductIsNotDeletedException;
import com.omega.bioway.ProductSearch.dataaccess.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDeleter {

    @Autowired
    private ProductRepository repository;

    public boolean execute(String productId){
        try{
            Optional<Product> check = repository.findById(productId);
            Product exist = check.get();
            repository.deleteById(productId);
            return true;
        }catch(IllegalArgumentException e){
            throw new ProductIsNotDeletedException("Could not delete product");
        }
        catch(NoSuchElementException e){
            throw new ProductIsNotDeletedException("Could not delete product that does not exist");
        }
    }
    
}
