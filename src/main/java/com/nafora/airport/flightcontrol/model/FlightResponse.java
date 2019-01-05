package com.nafora.airport.flightcontrol.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FlightResponse {
	private String flightNumber;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private Date landedOn;
	
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public Date getLandedOn() {
		return landedOn;
	}
	public void setLandedOn(Date landedOn) {
		this.landedOn = landedOn;
	}
	
	
}
