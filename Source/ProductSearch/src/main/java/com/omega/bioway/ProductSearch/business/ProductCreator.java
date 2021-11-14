package com.omega.bioway.ProductSearch.business;

import com.omega.bioway.ProductSearch.crosscutting.entities.Product;
import com.omega.bioway.ProductSearch.crosscutting.exceptions.ProductDidNotSaveException;
import com.omega.bioway.ProductSearch.dataaccess.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCreator {
    
    @Autowired
    private ProductRepository repository;

    public boolean execute(String productId, String supplierId, String productName, String productType, String productUri){
        Product newProduct = new Product(productName, productType, productUri, supplierId);
        newProduct.setId(productId);
        try{
            Product saved = repository.save(newProduct);
            Boolean sameName = newProduct.getName().equals(saved.getName());
            Boolean sameSupplierId = newProduct.getSupplierId().equals(saved.getSupplierId());
            Boolean sameProductType = newProduct.getType().equals(saved.getType());
            Boolean sameProductUri = newProduct.getUri().equals(saved.getUri());
            return (sameName && sameSupplierId && sameProductType && sameProductUri);
        }catch (IllegalArgumentException e){
            throw new ProductDidNotSaveException("Product did not save");
        }
    }
}
