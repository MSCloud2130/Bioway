package com.omega.bioway.payments.crosscutting.entities;

import com.omega.bioway.payments.crosscutting.exceptions.InvalidCard;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Document(collection = "card")
public class Card {

    @Id
    private String number;
    private String name;
    private String expirationDate;
    private String securityCode;

    public Card(String number, String name, String expirationDate, String securityCode) {
        this.number = number;
        this.name = name;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        validate(this);
    }

    public Card(){};

    public void validate(Card card) {
        if(card.getNumber().length() == 16){
            if(!card.getName().isBlank() && !card.getSecurityCode().isBlank()){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
                simpleDateFormat.setLenient(false);
                Date expiry = null;
                try {
                    expiry = simpleDateFormat.parse(card.getExpirationDate());
                    boolean expired = expiry.before(new Date());
                    if(!expired){
                        return;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
        throw new InvalidCard("Invalid card");
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
}
