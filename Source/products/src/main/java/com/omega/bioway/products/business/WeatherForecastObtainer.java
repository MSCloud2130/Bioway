package com.omega.bioway.products.business;

import com.omega.bioway.products.serviceaccess.weatherservice.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherForecastObtainer {

    @Autowired
    WeatherService service;

    public String[] execute(Double lat, Double lon, long firstDay, long lastDay){
        if(firstDay <= 16){
            String[] forecast = service.getWeatherForecast(lat.toString(),lon.toString());
            List<String> coveredDays = new ArrayList<>();
            int i = (int) firstDay;
            while(i < 16){
                coveredDays.add(forecast[i]);
                i++;
            }
            return (String[]) coveredDays.toArray();
        }
        return null;
    }
}
