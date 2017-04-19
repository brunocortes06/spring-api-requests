package com.bruno.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.api.Maplink;
import com.bruno.api.model.Address;
import com.bruno.api.model.Location;
import com.bruno.api.model.RouteResponse;
import com.bruno.api.model.RouteSummary;

@Service
public class ProcessService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessService.class);

	private final Maplink api;

	@Autowired
	ProcessService(Maplink api) {
		this.api = api;
	}

	public RouteResponse processsRoute(List<Address> addressData, BigDecimal costPerKm, boolean avoidTraffic) throws Exception {
		try {
			List<Location> points = new ArrayList<>();

			//Transform the given addresses to points lat lng, using Maplink API
			for (Address location : addressData)
				points.add(api.defineGeoLocation(location.getStreetName(), location.getNumber(), location.getCity(), location.getState()));

			RouteSummary route;
			//Call the route API
			if(!points.isEmpty()){
				route = api.defineRoute(points, avoidTraffic);
			}else if(points.size() < 2 ){
				throw new Exception("Insufficient locations was given!");
			}else if(points.stream().distinct().count() == 1){
				throw new Exception("Please, use different addresses.");
			}else{
				throw new Exception("No points returned to the given addresses!");
			}

			//Gets the distance and converts to Km to get the cost per km
			BigDecimal fuelCost = costPerKm.multiply(BigDecimal.valueOf(route.getDistance() / 1000));

			//Parse the response data
			RouteResponse routeData = new RouteResponse(
					route.getDuration(),route.getDistance(),fuelCost.setScale(2, BigDecimal.ROUND_HALF_UP),fuelCost.add(route.getTotalTollFeeAmount()).setScale(2, BigDecimal.ROUND_HALF_UP));

			LOGGER.info("Route done! distance={}, fuel_cost={}, route_time={}, tax_cost={}", routeData.getDistanceTotal(), routeData.getFuelCost(), routeData.getRouteTime(), routeData.getTaxCost());

			return routeData;
		} catch (Exception e) {
			LOGGER.info("Request error on processRoute! errorMessage={}", e.getMessage());
			throw new Exception(e.getMessage(), e);
		}
	}
}
