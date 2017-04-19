package com.bruno.api;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bruno.api.model.GeolocationResponse;
import com.bruno.api.model.LatLngResponse;
import com.bruno.api.model.Location;
import com.bruno.api.model.Route;
import com.bruno.api.model.RouteSummary;
import com.bruno.api.model.Routes;
import com.bruno.api.model.Status;
import com.bruno.api.properties.ApiUrls;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Maplink {

    private final Logger LOGGER = LoggerFactory.getLogger(Maplink.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final ApiUrls apiUrl = new ApiUrls();

    @Autowired
    Maplink(RestTemplateBuilder restTemplateBuilder, ObjectMapper mapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.mapper = mapper;
    }

    //Transform address into points lat lng
    public Location defineGeoLocation(String address, String number, String city, String state) throws Exception {
        String response = null;
        try {
            String url = apiUrl.getGeoCodingUrl(address, number, city, state);

            response = restTemplate.getForObject(url, String.class);

            //parse the response
            GeolocationResponse geolocation = mapper.readValue(response, GeolocationResponse.class);

            //verification
            if (geolocation.getAddresses() == null || geolocation.getAddresses().size() < 1)
                throw new Exception(String.format("Address not found: address=%s " + "number=%s city=%s state=%s", address, number, city, state));

            LatLngResponse geoAddress = geolocation.getAddresses().get(0);

            return new Location(geoAddress.getLocation().getLatitude(), geoAddress.getLocation().getLongitude());
        } catch (IOException e) {
            throw new RuntimeException("Get position error: " + response, e);
        }
    }

    //define the route
    public RouteSummary defineRoute(List<Location> positions, boolean avoidTraffic) throws Exception {

        String response = null;
        try {
        	//Creates a request
            String url = apiUrl.getUrl(positions, avoidTraffic);
            LOGGER.debug("Requested URL " + url);

            //get response
            response = restTemplate.getForObject(url, String.class);

            //look the status returned, it may obey the HTTP codes
            Status status = mapper.readValue(response, Status.class);
            
            if(status.getCode().getCode().equals("OK")){
            	LOGGER.debug("Request CODE={}", status.getCode().getCode());
            }else{
            	throw new Exception(String.format("Request NOT OK CODE={}", status.getCode().getCode()));
            }
            
            //parse the response
            Routes routesResponse = mapper.readValue(response, Routes.class);

            if (routesResponse.getRoutes() == null || routesResponse.getRoutes().size() < 1)
                throw new Exception(String.format("No routes found for provided positions. positions=%s",
                        positions));

            Route route = routesResponse.getRoutes().get(0);

            return new RouteSummary(route.getSummary().getDuration(), route.getSummary().getDistance(),
                    route.getSummary().getTotalTollFees());
        } catch (IOException e) {
            throw new RuntimeException("Response error! response=" + response, e);
        }
    }

}