package com.omega.bioway.payments.business;

import com.omega.bioway.payments.crosscutting.entities.Card;
import com.omega.bioway.payments.crosscutting.entities.Payment;
import com.omega.bioway.payments.dataaccess.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardCreator {

    @Autowired
    CardRepository repository;

    @Autowired
    CardFinder finder;

    public void execute(Card card){
        if(!finder.execute(card.getNumber())){
            repository.save(card);
        }
    }

}
