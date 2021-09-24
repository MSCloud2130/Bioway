package com.omega.bioway.payments.business;

import com.omega.bioway.payments.crosscutting.entities.Card;
import com.omega.bioway.payments.dataaccess.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CardFinder {
    @Autowired
    CardRepository repository;

    public Boolean execute(String number){
        Optional<Card> optionalCard = repository.findById(number);
        if (!optionalCard.isEmpty())
            return true;
        return false;
    }
}
