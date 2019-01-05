package com.nafora.airport.component.flightcontrol.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nafora.airport.component.flightcontrol.exception.ServiceException;
import com.nafora.airport.flightcontrol.model.FlightDetails;

@Service(value="flightControlService")
public interface FlightControlService {

	/**
	 * checks if a given flight can land.
	 * 
	 * @param flightDetails
	 * @return true if allowed to land, otherwise false.
	 * @throws ServiceException
	 */
	boolean retrieveLandingStatus(FlightDetails flightDetails) throws ServiceException;
	/**
	 * gets details of all the flights that landed.
	 * 
	 * @return list of Flight Details.
	 * @throws ServiceException
	 */
	List<FlightDetails> getLandedFlightDetails()  throws ServiceException;
}
