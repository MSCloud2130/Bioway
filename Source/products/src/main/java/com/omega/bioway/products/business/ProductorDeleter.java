package com.omega.bioway.products.business;

import com.omega.bioway.products.crosscutting.entities.Product;
import com.omega.bioway.products.crosscutting.exceptions.ProductNotDeletedException;
import com.omega.bioway.products.crosscutting.exceptions.ProductNotFoundException;
import com.omega.bioway.products.dataaccess.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductorDeleter {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductFinder productFinder;

    public void execute(String productId) {

        try {
            Product product = productFinder.execute(productId);
            repository.deleteById(productId);
        } catch (ProductNotFoundException e) {
            throw e;
        } catch (Exception e)
        {
            throw new ProductNotDeletedException("Product not deleted");
        }
    }
}