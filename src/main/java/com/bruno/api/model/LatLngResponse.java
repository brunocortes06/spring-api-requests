package com.bruno.api.model;

import com.bruno.api.properties.LatLngProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LatLngResponse {
	
    private LatLngProperties location;

    public LatLngProperties getLocation() {
        return location;
    }

    public void setLocation(LatLngProperties location) {
        this.location = location;
    }
}
