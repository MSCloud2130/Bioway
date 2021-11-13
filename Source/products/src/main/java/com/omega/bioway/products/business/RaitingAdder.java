package com.omega.bioway.products.business;
import com.omega.bioway.products.crosscutting.entities.Product;
import com.omega.bioway.products.crosscutting.entities.ProductCustomer;
import com.omega.bioway.products.crosscutting.entities.Rating;
import com.omega.bioway.products.crosscutting.exceptions.CalificationOutOfBondaries;
import com.omega.bioway.products.dataaccess.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaitingAdder {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductFinder productFinder;

    public void execute(String productId, int value, String comment, String userId, String name, String resource) {
        if(value > 5 || value < 0)
            throw new CalificationOutOfBondaries("Calification out of bondaries");
        Product product = productFinder.execute(productId);
        ProductCustomer newProductConsumer = new ProductCustomer(userId,name);
        Rating newRating = new Rating(value,comment,newProductConsumer);
        product.addRating(newRating);
        repository.save(product);
    }
}
