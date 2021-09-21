package com.omega.bioway.products.business;

import com.omega.bioway.products.crosscutting.entities.Product;
import com.omega.bioway.products.crosscutting.entities.Question;
import com.omega.bioway.products.dataaccess.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductQuestionAdder {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductFinder productFinder;

    public void execute(String productId, String questionContent, String userId, String userName) {
        Product product = productFinder.execute(productId);
        Question newQuestion = new Question(questionContent, userId, userName);
        product.addQuestion(newQuestion);
        repository.save(product);
    }

}
