package com.nafora.airport.flightcontrol.model;

import java.util.Date;

public class FlightDetails {
	private String flightNumber;
	private FlightType flightType;
	private Date landingAcceptedOn;
	
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
	public Date getLandingAcceptedOn() {
		return landingAcceptedOn;
	}
	public void setLandingAcceptedOn(Date landingAcceptedOn) {
		this.landingAcceptedOn = landingAcceptedOn;
	}
	
	@Override
	public String toString() {
		return "FlightDetails [flightNumber=" + flightNumber + ", flightType=" + flightType + ", landingAcceptedOn="
				+ landingAcceptedOn + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flightNumber == null) ? 0 : flightNumber.hashCode());
		result = prime * result + ((flightType == null) ? 0 : flightType.hashCode());
		result = prime * result + ((landingAcceptedOn == null) ? 0 : landingAcceptedOn.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightDetails other = (FlightDetails) obj;
		if (flightNumber == null) {
			if (other.flightNumber != null)
				return false;
		} else if (!flightNumber.equals(other.flightNumber))
			return false;
		if (flightType != other.flightType)
			return false;
		if (landingAcceptedOn == null) {
			if (other.landingAcceptedOn != null)
				return false;
		} else if (!landingAcceptedOn.equals(other.landingAcceptedOn))
			return false;
		return true;
	}
	
}
