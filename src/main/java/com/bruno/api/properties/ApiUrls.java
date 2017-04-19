package com.bruno.api.properties;

import java.util.List;

import com.bruno.api.model.Location;

public class ApiUrls {

	public ApiUrls (){

	}

	public static final String GEOCODING_API = "https://api.maplink.com.br/v0/search?types=address,street" + "&token=z0vmywzpbCSLdJYl5mUk5m2jNGytNGt6NJu6NGU=&limit=1&q=%s, %s - %s - %s";
	public static final String ROUTE_API = "https://api.maplink.com.br/v1/route?" + "token=z0vmywzpbCSLdJYl5mUk5m2jNGytNGt6NJu6NGU=&result=summary.distance,summary.duration,summary.tolls" +
			"&travel.vehicle=Car";
	public static final String WAYPOINT = "&waypoint.%s.latlng=%s,%s";
	public static final String AVOID_TRAFFIC = "&traffic.avoid=true";

	public String getUrl(List<Location> locationList, boolean avoidTraffic) {
		
		if(locationList.size() == 0)
			return "";
		
		StringBuilder url = new StringBuilder(ApiUrls.ROUTE_API);
		
		int i = 0;
		
		for (Location loc : locationList){
			url.append(String.format(ApiUrls.WAYPOINT, i++, loc.getLatitude(), loc.getLongitude()));
		}
		
		if(avoidTraffic)
			url.append(AVOID_TRAFFIC);
		
		return url.toString();
	}

	public String getGeoCodingUrl(String address, String number, String city, String state) {
		return String.format(GEOCODING_API, address, number, city, state);
	}

}
