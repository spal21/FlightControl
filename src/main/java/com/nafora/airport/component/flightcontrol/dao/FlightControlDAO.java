package com.nafora.airport.component.flightcontrol.dao;

import java.io.IOException;
import java.util.List;

import com.nafora.airport.flightcontrol.model.FlightDetails;

public interface FlightControlDAO {
	
	/**
	 * Save the flightDetails.
	 * 
	 * @param flightDetails
	 * @throws IOException
	 */
	void saveFlightDetails(FlightDetails flightDetails) throws IOException;
	/**
	 * Get All flight details.
	 * 
	 * @return
	 * @throws IOException
	 */
	List<FlightDetails> getAllFlightDetails() throws IOException;
	/**
	 * Get details for given flight.
	 * 
	 * @param flightNumber
	 * @return
	 * @throws IOException
	 */
	FlightDetails getLatestFlightDetails(String flightNumber) throws IOException;
}
