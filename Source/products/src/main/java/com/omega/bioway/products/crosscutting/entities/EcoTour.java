package com.omega.bioway.products.crosscutting.entities;

import java.time.LocalDateTime;

public class EcoTour extends Product{

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime departureLocation;
    private LocalDateTime arrivalLocation;

    public EcoTour() {
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(LocalDateTime departureLocation) {
        this.departureLocation = departureLocation;
    }

    public LocalDateTime getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(LocalDateTime arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }
}
