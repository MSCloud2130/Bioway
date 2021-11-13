package com.omega.bioway.products.serviceaccess.weatherservice;

import com.omega.bioway.products.crosscutting.entities.WeatherResponse;

public interface WeatherService {
    public WeatherResponse getWeatherForecast(String lat, String lon);
}
