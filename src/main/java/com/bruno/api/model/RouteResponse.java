package com.bruno.api.model;

import java.math.BigDecimal;

public class RouteResponse {
	
	private Long routeTime;
	private Double distanceTotal;	
	private BigDecimal fuelCost;	
	private BigDecimal taxCost;
	
	public RouteResponse() {
		super();
	}
	
	public RouteResponse(Long routeTime, Double distanceTotal, BigDecimal fuelCost, BigDecimal taxCost) {
		super();
		this.routeTime     = routeTime;
		this.distanceTotal = distanceTotal;
		this.fuelCost      = fuelCost;
		this.taxCost       = taxCost;
	}

	public Long getRouteTime() {
		return routeTime;
	}

	public void setRouteTime(Long routeTime) {
		this.routeTime = routeTime;
	}

	public Double getDistanceTotal() {
		return distanceTotal;
	}

	public void setDistanceTotal(Double distanceTotal) {
		this.distanceTotal = distanceTotal;
	}

	public BigDecimal getFuelCost() {
		return fuelCost;
	}

	public void setFuelCost(BigDecimal fuelCost) {
		this.fuelCost = fuelCost;
	}

	public BigDecimal getTaxCost() {
		return taxCost;
	}

	public void setTaxCost(BigDecimal taxCost) {
		this.taxCost = taxCost;
	}
	

}
