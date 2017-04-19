package com.bruno.api.model;

public class Location {

    private final String lat;
    private final String lng;

    public Location(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLatitude() {
        return lat;
    }

    public String getLongitude() {
        return lng;
    }
}
