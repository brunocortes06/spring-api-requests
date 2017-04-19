package com.bruno.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.api.model.Address;
import com.bruno.api.model.RouteResponse;
import com.bruno.service.ProcessService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


//Here is the main API processor
@RestController
public class Process {

	private static final Logger LOGGER = LoggerFactory.getLogger(Process.class);

	private final ProcessService processService;
	private final ObjectMapper mapper;

	@Autowired
	public Process(ProcessService processService, ObjectMapper objectMapper) {
		this.processService = processService;
		this.mapper = objectMapper;
	}

	//Get method to get the request as specified on the dev challenge
	@GetMapping(path = "/route/{addresses}", produces = "application/json")
	public RouteResponse routeByGet(@PathVariable("addresses") String locations, @RequestParam(defaultValue = "1.09") BigDecimal kmCost, @RequestParam("avoidTraffic" )	boolean avoidTraffic) throws Exception {

		try {
			//Get addresses from streetName tag and create a list
			List<Address> addresses = mapper.readValue(locations, new TypeReference<List<Address>>() {} );

			LOGGER.debug("Request received with: avoidTraffic={}, kmCost={}" , avoidTraffic, kmCost);
			if(LOGGER.isDebugEnabled()){
				for(Address adr : addresses){
					LOGGER.debug("Endereços pesquisados: addresses={}" , adr.getStreetName());
				}
			}

			//Start the process, parse the response and return it
			RouteResponse routeInformation = processService.processsRoute(toLocationInfo(addresses), kmCost, avoidTraffic);
			LOGGER.info("Response Ok", routeInformation.toString());

			return routeInformation;
		} catch (IOException e) {
			throw new Exception("Request error! ", e);
		}
	}

	private List<Address> toLocationInfo(List<Address> locations) {
		return locations.stream().map(Address::toLocationInfo).collect(Collectors.toList());
	}

}
