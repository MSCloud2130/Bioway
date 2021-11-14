package com.omega.bioway.ProductSearch.business;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.omega.bioway.ProductSearch.crosscutting.entities.Product;
import com.omega.bioway.ProductSearch.crosscutting.exceptions.ProductIsNotEditedException;
import com.omega.bioway.ProductSearch.dataaccess.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductEditor {
    
    @Autowired
    private ProductRepository repository;

    public boolean execute(String productId, String productName, String productUri){
        try{
            Optional<Product> check = repository.findById(productId);
            Product exist = check.get();
            exist.setName(productName);
            exist.setUri(productUri);
            repository.save(exist);
            return true;
        }catch (IllegalArgumentException e){
            throw new ProductIsNotEditedException("Product did not edit");
        }catch(NoSuchElementException e){
            throw new ProductIsNotEditedException("Could not edit product that does not exist");
        }
    }
}