package com.omega.bioway.products.crosscutting.entities;

import java.time.LocalDateTime;

public class Accommodation extends Product{

    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Location location;

    public Accommodation() {
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
