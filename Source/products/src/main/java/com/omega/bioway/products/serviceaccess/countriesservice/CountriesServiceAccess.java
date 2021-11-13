package com.omega.bioway.products.serviceaccess.countriesservice;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.omega.bioway.products.crosscutting.entities.CountryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CountriesServiceAccess implements CountriesService{

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public CountryData getCountryData(String name) {
        ResponseEntity<List<CountryData>> response
                = restTemplate.exchange( "https://restcountries.com/v3.1/alpha?codes=" + name,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CountryData>>() {});
        System.out.println(response.getBody());
        return response.getBody().get(0);
    }


}
