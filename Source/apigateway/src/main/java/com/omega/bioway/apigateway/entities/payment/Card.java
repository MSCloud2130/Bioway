package com.omega.bioway.apigateway.entities.payment;

public class Card {

    private String number;
    private String name;
    private String expirationDate;
    private String securityCode;

    public Card() {
    }

    public Card(String number, String name, String expirationDate, String securityCode) {
        this.number = number;
        this.name = name;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
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
