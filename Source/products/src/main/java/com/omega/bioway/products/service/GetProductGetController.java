package com.omega.bioway.products.service;

import com.omega.bioway.products.business.CountryDataObtainer;
import com.omega.bioway.products.business.ProductFinder;
import com.omega.bioway.products.business.WeatherForecastObtainer;
import com.omega.bioway.products.crosscutting.dto.WeatherResponse;
import com.omega.bioway.products.crosscutting.entities.*;
import com.omega.bioway.products.crosscutting.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
public class GetProductGetController {

    @Autowired
    private ProductFinder productFinder;

    @Autowired
    private CountryDataObtainer countryDataObtainer;

    @Autowired
    private WeatherForecastObtainer weatherForecastObtainer;

    @GetMapping(value = "products/{id}", produces = "application/json")
    public ResponseEntity execute(@PathVariable String id){
        Product product = productFinder.execute(id);
        if(product instanceof Accommodation || product instanceof  EcoTour){
            ProductResponse productResponse = new ProductResponse();
            productResponse.setProduct(product);
            Location location = new Location();

            long starsInDays = 0;
            long endsInDays = 0;
            if( product instanceof Accommodation){
                location = ((Accommodation) product).getLocation();

                starsInDays = Duration.between(LocalDateTime.now(), ((Accommodation) product).getCheckIn()).toDays();
                endsInDays = Duration.between(LocalDateTime.now(), ((Accommodation) product).getCheckOut()).toDays();
            }
            else if(product instanceof EcoTour){
                location = ((EcoTour) product).getArrivalLocation();

                starsInDays = Duration.between(LocalDateTime.now(), ((EcoTour) product).getArrivalTime()).toDays();
                endsInDays = Duration.between(LocalDateTime.now(), ((EcoTour) product).getDepartureTime()).toDays();
            }


            productResponse.setCountryData( countryDataObtainer.execute( location.getCountry() ) );

            WeatherResponse forecast = weatherForecastObtainer.execute( location.getLatitude(), location.getLongitude(), starsInDays, endsInDays);
            if(forecast != null){
                productResponse.setWeatherData(forecast);
            }
            return ResponseEntity.status(HttpStatus.OK).body(productResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity handleProductNotFoundException(ProductNotFoundException exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception exception){
        HashMap<String,String> response = new HashMap<>(){{
            put("error",exception.getMessage());
        }};
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
