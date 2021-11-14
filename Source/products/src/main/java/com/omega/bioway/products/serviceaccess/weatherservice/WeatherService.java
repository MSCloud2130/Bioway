package com.omega.bioway.products.serviceaccess.weatherservice;

import com.omega.bioway.products.crosscutting.dto.WeatherResponse;

public interface WeatherService {
    public WeatherResponse getWeatherForecast(String lat, String lon);
}
