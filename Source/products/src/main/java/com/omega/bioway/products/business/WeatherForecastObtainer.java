package com.omega.bioway.products.business;

import com.omega.bioway.products.crosscutting.dto.WeatherResponse;
import com.omega.bioway.products.serviceaccess.weatherservice.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherForecastObtainer {

    @Autowired
    WeatherService service;

    public WeatherResponse execute(Double lat, Double lon, long firstDay, long lastDay){
        if(firstDay <= 16){
            WeatherResponse forecast = service.getWeatherForecast(lat.toString(),lon.toString());
            List<Object> coveredDays = new ArrayList<>();
            int i = (int) firstDay;
            for(Object data : forecast.getData()){
                coveredDays.add(data);
                i++;
                if(i > 16){
                    break;
                }
            }
            WeatherResponse weatherResponse = new WeatherResponse();
            weatherResponse.setData(coveredDays);
            weatherResponse.setCity_name( forecast.getCity_name() );
            weatherResponse.setTimezone( forecast.getTimezone() );
            return weatherResponse;
        }
        return null;
    }
}
