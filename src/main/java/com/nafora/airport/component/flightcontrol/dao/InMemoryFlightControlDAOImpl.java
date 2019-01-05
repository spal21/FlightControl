package com.nafora.airport.component.flightcontrol.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nafora.airport.flightcontrol.model.FlightDetails;

/*
 * In-Memory implementation of the DAO.
 *  */
@Component
public class InMemoryFlightControlDAOImpl implements FlightControlDAO{

	private ConcurrentHashMap<String, List<FlightDetails>> map = new ConcurrentHashMap<>();
	
	private static final Logger LOGGER = Logger.getLogger(InMemoryFlightControlDAOImpl.class.getName());
	@Override
	public void saveFlightDetails(FlightDetails flightDetails) throws IOException {
		LOGGER.log(Level.INFO, " saveFlightDetails : "+flightDetails);
		map.computeIfAbsent(flightDetails.getFlightNumber(), list -> new ArrayList<>()).add(flightDetails);
	}

	@Override
	public List<FlightDetails> getAllFlightDetails() throws IOException {
		LOGGER.log(Level.INFO, "getAllFlightDetails invoked");
		return map.entrySet().stream().map(e -> e.getValue()).flatMap(l -> l.stream()).collect(Collectors.toList());
	}

	@Override
	public FlightDetails getLatestFlightDetails(String flightNumber) throws IOException {
		LOGGER.log(Level.INFO, "getLatestFlightDetails for : "+flightNumber);
		List<FlightDetails> list  = map.get(flightNumber);
		return list.get(list.size()-1);
	}
}
