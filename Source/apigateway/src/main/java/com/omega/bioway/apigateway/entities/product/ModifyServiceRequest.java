package com.omega.bioway.apigateway.entities.product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ModifyServiceRequest {

    private double price;
    private String name;
    private List<String> links;
    private List<String> pictures;
    private String description;
    Optional<String> transportType;
    Optional<LocalDateTime> departureTime;
    Optional<LocalDateTime> arrivalTime;
    Optional<LocalDateTime> checkIn;
    Optional<LocalDateTime> checkOut;
    Optional<List<String>> includedItems;
    private Optional<Location> location;
    private Optional<Location> arrivalLocation;
    private Optional<Location> departureLocation;

    public ModifyServiceRequest() {
    }

    public ModifyServiceRequest(double price, String name, List<String> links, List<String> pictures, String description,
                                Optional<String> transportType, Optional<LocalDateTime> departureTime,
                                Optional<LocalDateTime> arrivalTime, Optional<LocalDateTime> checkIn,
                                Optional<LocalDateTime> checkOut, Optional<List<String>> includedItems, Optional<Location> location,
                                Optional<Location> arrivalLocation, Optional<Location> departureLocation) {
        this.price = price;
        this.name = name;
        this.links = links;
        this.pictures = pictures;
        this.description = description;
        this.transportType = transportType;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.includedItems = includedItems;
        this.location = location;
        this.arrivalLocation = arrivalLocation;
        this.departureLocation = departureLocation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Optional<String> getTransportType() {
        return transportType;
    }

    public void setTransportType(Optional<String> transportType) {
        this.transportType = transportType;
    }

    public Optional<LocalDateTime> getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Optional<LocalDateTime> departureTime) {
        this.departureTime = departureTime;
    }

    public Optional<LocalDateTime> getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Optional<LocalDateTime> arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Optional<LocalDateTime> getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Optional<LocalDateTime> checkIn) {
        this.checkIn = checkIn;
    }

    public Optional<LocalDateTime> getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Optional<LocalDateTime> checkOut) {
        this.checkOut = checkOut;
    }

    public Optional<List<String>> getIncludedItems() {
        return includedItems;
    }

    public void setIncludedItems(Optional<List<String>> includedItems) {
        this.includedItems = includedItems;
    }

    public Optional<Location> getLocation() {
        return location;
    }

    public void setLocation(Optional<Location> location) {
        this.location = location;
    }

    public Optional<Location> getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(Optional<Location> arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public Optional<Location> getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(Optional<Location> departureLocation) {
        this.departureLocation = departureLocation;
    }
}
