package com.omega.bioway.products.serviceaccess.weatherservice;

import com.omega.bioway.products.crosscutting.dto.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceAccess implements WeatherService{

    @Autowired
    private RestTemplate restTemplate;

    @Bean("weather")
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public WeatherResponse getWeatherForecast(String lat, String lon) {
        try{
            ResponseEntity<WeatherResponse> response
                    = restTemplate.exchange( "https://api.weatherbit.io/v2.0/forecast/daily?&lat="
                            + lat + "&lon=" + lon + "&key=cba0b893c74146b597edc2546ff73a08",
                    HttpMethod.GET, null, WeatherResponse.class);
            return response.getBody();
        }catch ( Exception e){
            e.printStackTrace();
            return null;
        }


    }
}
