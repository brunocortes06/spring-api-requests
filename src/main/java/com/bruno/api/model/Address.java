package com.bruno.api.model;

public class Address {
	
	private long id;	
	
	private String streetName;
	private String number;	
	private String city;
	private String state;
	
	public Address() {
		super();
	}
	
	public Address(String streetName, String number, String city, String state) {
		super();
		this.streetName = streetName;
		this.number     = number;
		this.city       = city;
		this.state      = state;
	}
	
    public Address toLocationInfo() {
        return new Address(streetName, number, city, state);
    }
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
