package com.omega.bioway.products.business;

import com.omega.bioway.products.crosscutting.entities.ProductCustomer;
import com.omega.bioway.products.crosscutting.entities.Question;
import com.omega.bioway.products.crosscutting.entities.Transport;
import com.omega.bioway.products.crosscutting.exceptions.ProductNotFoundException;
import com.omega.bioway.products.dataaccess.ProductRepository;
import com.omega.bioway.products.crosscutting.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductFinder {

    @Autowired
    private ProductRepository repository;

    public Product execute(String id){
        Optional<Product> requestedProduct = repository.findById(id);
        if (requestedProduct.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }
        return requestedProduct.get();
    }

    //TODO: REMOVE
    public void addTestData() {
        Transport transportProduct = new Transport();
        transportProduct.setName("Transporte a Parque Tayrona");
        transportProduct.setDescription("Transporte desde el areopuerto de Santa Marta al Parque Tayrona");
        List<String> transportLinks = new ArrayList<>();
        transportLinks.add("https://www.youtube.com/");
        transportProduct.setLinks(transportLinks);

        ProductCustomer customer = new ProductCustomer();
        customer.setId("12345");
        customer.setName("Pedro Perez");
        customer.setResource("recurso.com");

        Question question = new Question();
        question.setDate(LocalDateTime.now());
        question.setContent("Cuanto dura el trayecto?");
        question.setAuthor(customer);
        transportProduct.addQuestion(question);

        transportProduct.setTransportType("Van");

        repository.save(transportProduct);
    }

}
