package com.omega.bioway.products.crosscutting.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryData {
    private Name name;
    private Boolean independent;
    private Boolean unMember;
    @JsonProperty("capital")
    private String[] capital;
    private String region;
    private String subregion;
    private Boolean landlocked;
    @JsonProperty("borders")
    private String[] borders;
    private String area;
    private Long population;
    private String[] timezones;
    @JsonProperty("flags")
    private CountryFlag flags;

    public CountryData() {
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Boolean getIndependent() {
        return independent;
    }

    public void setIndependent(Boolean independent) {
        this.independent = independent;
    }

    public Boolean getUnMember() {
        return unMember;
    }

    public void setUnMember(Boolean unMember) {
        this.unMember = unMember;
    }

    public String[] getCapital() {
        return capital;
    }

    public void setCapital(String[] capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }


    public Boolean getLandlocked() {
        return landlocked;
    }

    public void setLandlocked(Boolean landlocked) {
        this.landlocked = landlocked;
    }

    public String[] getBorders() {
        return borders;
    }

    public void setBorders(String[] borders) {
        this.borders = borders;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public String[] getTimezones() {
        return timezones;
    }

    public void setTimezones(String[] timezones) {
        this.timezones = timezones;
    }

    public CountryFlag getFlags() {
        return flags;
    }

    public void setFlags(CountryFlag flags) {
        this.flags = flags;
    }
}
