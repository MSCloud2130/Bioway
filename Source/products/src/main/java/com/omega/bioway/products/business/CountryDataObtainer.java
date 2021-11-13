package com.omega.bioway.products.business;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.omega.bioway.products.crosscutting.entities.Accommodation;
import com.omega.bioway.products.crosscutting.entities.CountryData;
import com.omega.bioway.products.crosscutting.entities.Product;
import com.omega.bioway.products.serviceaccess.countriesservice.CountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryDataObtainer {

    @Autowired
    CountriesService service;

    public CountryData execute(String country) {
        return service.getCountryData(country);
    }
}
