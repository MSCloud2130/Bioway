package com.omega.bioway.products.crosscutting.entities;

import com.fasterxml.jackson.databind.util.JSONPObject;

public class ProductResponse {
    private Product product;
    private CountryData countryData;
    private WeatherResponse weatherData;

    public ProductResponse(){

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CountryData getCountryData() {
        return countryData;
    }

    public void setCountryData(CountryData countryData) {
        this.countryData = countryData;
    }

    public WeatherResponse getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherResponse weatherData) {
        this.weatherData = weatherData;
    }
}
