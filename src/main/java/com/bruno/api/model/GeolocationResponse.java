package com.bruno.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeolocationResponse {

    @JsonProperty("results")
    private List<LatLngResponse> latlngResp;

    public List<LatLngResponse> getAddresses() {
        return latlngResp;
    }

    public void setAddresses(List<LatLngResponse> latlngResp) {
        this.latlngResp = latlngResp;
    }
}