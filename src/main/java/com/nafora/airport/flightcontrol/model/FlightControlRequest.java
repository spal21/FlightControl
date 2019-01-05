package com.nafora.airport.flightcontrol.model;

public class FlightControlRequest {

	private String flightNumber;
	private FlightType flightType;
	
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public FlightType getFlightType() {
		return flightType;
	}
	public void setFlightType(FlightType flightType) {
		this.flightType = flightType;
	}
	
	@Override
	public String toString() {
		return "FlightControlRequest [flightNumber=" + flightNumber + ", flightType=" + flightType + "]";
	}
	
	
}
