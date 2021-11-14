package com.omega.bioway.products.business;

import com.omega.bioway.products.serviceaccess.countriesservice.CountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryDataObtainer {

    @Autowired
    CountriesService service;

    public Object execute(String country) {
        return service.getCountryData(country);
    }
}
