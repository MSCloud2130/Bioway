package com.omega.bioway.products.crosscutting.entities;

public class Location {

    private double latitude;
    private double longitude;
    private String address;
    private String country;

    public Location() {
    }

    public Location(double latitude, double longitude, String address, String country) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
