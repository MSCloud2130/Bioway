package com.omega.bioway.products.serviceaccess.countriesservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountriesServiceAccess implements CountriesService{

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public Object getCountryData(String name) {
        ResponseEntity response
                = restTemplate.exchange( "https://restcountries.com/v3.1/alpha?codes=" + name,
                HttpMethod.GET, null, Object.class);

        return response.getBody();
    }


}
