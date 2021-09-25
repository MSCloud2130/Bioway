package com.omega.bioway.ProductSearch.business;

import java.util.List;

import com.omega.bioway.ProductSearch.crosscutting.entities.Product;
import com.omega.bioway.ProductSearch.crosscutting.exceptions.ProductsNotFoundException;
import com.omega.bioway.ProductSearch.dataaccess.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeFilter {
    
    @Autowired
    private ProductRepository repository;

    public List<Product> execute(String type){
        List<Product> products = repository.findByTypeRegex(".*"+type+".*");
        if(products.isEmpty()){
            throw new ProductsNotFoundException("Products not found");
        }
        return products;
    }

}
