package com.omega.bioway.products.business;

import com.omega.bioway.products.crosscutting.entities.Product;
import com.omega.bioway.products.crosscutting.entities.Question;
import com.omega.bioway.products.crosscutting.exceptions.QuestionNotFoundException;
import com.omega.bioway.products.dataaccess.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionAnswerSetter {

    @Autowired
    private ProductFinder productFinder;
    @Autowired
    private ProductRepository repository;

    public void execute(String productId, String questionId, String answerContent){
        Product product = productFinder.execute(productId);
        Question question = null;
        for (Question q : product.getQuestions()) {
            if (q.getId().equals(questionId)) {
                question = q;
                break;
            }
        }
        if (question == null) {
            throw new QuestionNotFoundException("Question not found");
        }
        question.setAnswer(answerContent);
        repository.save(product);
    }

}
