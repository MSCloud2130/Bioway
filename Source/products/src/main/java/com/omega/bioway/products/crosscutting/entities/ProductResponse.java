package com.omega.bioway.products.crosscutting.entities;

public class ProductResponse {
    private Product product;
    private String countryData;
    private String[] weatherData;

    public ProductResponse(){

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCountryData() {
        return countryData;
    }

    public void setCountryData(String countryData) {
        this.countryData = countryData;
    }

    public String[] getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(String[] weatherData) {
        this.weatherData = weatherData;
    }
}
