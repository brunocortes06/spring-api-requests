package com.bruno.api.model;

public class Place {
	

    private final String latitude;
    private final String longitude;

    public Place(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

}
