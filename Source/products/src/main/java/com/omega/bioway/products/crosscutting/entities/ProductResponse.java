package com.omega.bioway.products.crosscutting.entities;

import com.omega.bioway.products.crosscutting.dto.WeatherResponse;

public class ProductResponse {
    private Product product;
    private Object countryData;
    private WeatherResponse weatherData;

    public ProductResponse(){

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Object getCountryData() {
        return countryData;
    }

    public void setCountryData(Object countryData) {
        this.countryData = countryData;
    }

    public WeatherResponse getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherResponse weatherData) {
        this.weatherData = weatherData;
    }
}
