package com.omega.bioway.ProductSearch.business;

import java.util.ArrayList;
import java.util.List;

import com.omega.bioway.ProductSearch.crosscutting.entities.Product;
import com.omega.bioway.ProductSearch.crosscutting.exceptions.ProductsNotFoundException;
import com.omega.bioway.ProductSearch.dataaccess.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSearcher {
    
    @Autowired
    private ProductRepository repository;

    public List<Product> execute(String name, String type, String supplierId){
        List<Product> products = new ArrayList<>();
        boolean hasName = name != null;
        boolean hasType = type != null;
        boolean hasSupplierId = supplierId != null;
        
        //no parameters received
        if(!hasName && !hasType && !hasSupplierId){
            products = repository.findAll();
        }
        //has name and type and supplierId
        else if(hasName && hasType && hasSupplierId){
            products = repository.findByNameAndTypeAndSupplierId(name, type, supplierId);
        }
        //has name
        else if (hasName){
            //has name and type
            if(hasType){
                products = repository.findByNameAndType(name, type);
            }
            //has name and supplierId
            else if(hasSupplierId){
                products = repository.findByNameAndSupplierId(name, supplierId);
            }
            //has only name
            else{
                products = repository.findByNameRegex(name);
            }
        }
        //has type
        else if (hasType){
            //has type and supplierId
            if(hasSupplierId){
                products = repository.findByTypeAndSupplierId(type, supplierId);
            }
            //has only type
            else{
                products = repository.findByTypeRegex(type);
            }
        }
        //has only supplierId
        else{
            products = repository.findBySupplierId(supplierId);
        }

        if(products.isEmpty()){
            throw new ProductsNotFoundException("Products not found");
        }
        return products;
    }
}
