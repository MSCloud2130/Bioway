package com.omega.bioway.products.serviceaccess.countriesservice;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.omega.bioway.products.crosscutting.entities.CountryData;

public interface CountriesService {
    public CountryData getCountryData(String name);
}
