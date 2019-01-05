package com.nafora.airport.component.flightcontrol.services;

import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nafora.airport.component.flightcontrol.dao.FlightControlDAO;
import com.nafora.airport.component.flightcontrol.exception.ServiceException;
import com.nafora.airport.flightcontrol.model.FlightDetails;
import com.nafora.airport.flightcontrol.model.FlightType;

@Component
public class FlightControlServiceImpl implements FlightControlService{

	@Autowired
	private FlightControlDAO flightControlDAO;
	
	@Value( "${landing.bigRunways}" )
	private int bigSlotsAvailable;
	
	@Value( "${landing.smallRunways}" )
	private int smallSlotsAvailable;
	
	@Value( "${landing.duration}" )
	private long duration;
	
	private Queue<FlightDetails> bigFlightQueue = new LinkedBlockingQueue<>();
	private Queue<FlightDetails> smallFlightQueue = new LinkedBlockingQueue<>();
	
	private static final Logger LOGGER = Logger.getLogger(FlightControlServiceImpl.class.getName());
	
	@Override
	public boolean retrieveLandingStatus(FlightDetails flightDetails) throws ServiceException  {
		LOGGER.log(Level.INFO, " retrieveLandingStatus for : "+flightDetails);
		try {
			FlightDetails tempBig = null;
			FlightDetails tempSmall = null;
			if (flightDetails.getFlightType() == FlightType.BIG) {
				if (bigFlightQueue.size() > 0) {
					LOGGER.log(Level.FINE, " retrieveLandingStatus for Big runway : checking for avaialblility");
					while ((tempBig = bigFlightQueue.peek()) != null
							&& (tempBig.getLandingAcceptedOn().getTime() + duration <= System.currentTimeMillis())) {
						bigFlightQueue.poll();
					}
					/*if (tempBig!=null && tempBig.getFlightNumber().equals(flightDetails.getFlightNumber())) {
						throw new ServiceException("Duplicate FLight Number");
					}*/
				}
				if (bigFlightQueue.size() < bigSlotsAvailable) {
					LOGGER.log(Level.FINE, " retrieveLandingStatus for Big runway : is available");
					bigFlightQueue.offer(flightDetails);
				} else {
					LOGGER.log(Level.FINE, " retrieveLandingStatus for Big runway : is full");
					return false;
				}

			} else if (flightDetails.getFlightType() == FlightType.SMALL) {
				if (smallFlightQueue.size() > 0) {
					LOGGER.log(Level.FINE, " retrieveLandingStatus for Small runway : checking for avaialblility");
					while ((tempSmall = smallFlightQueue.peek()) != null
							&& (tempSmall.getLandingAcceptedOn().getTime() + duration  <= System.currentTimeMillis())) {
						smallFlightQueue.poll();
					}
					/*if (tempSmall!=null && tempSmall.getFlightNumber().equals(flightDetails.getFlightNumber())) {
						throw new ServiceException("Duplicate FLight Number");
					}*/
				}
				if (smallFlightQueue.size() < smallSlotsAvailable) {
					LOGGER.log(Level.FINE, " retrieveLandingStatus for Small runway : is available");
					smallFlightQueue.offer(flightDetails);
				} else {
					LOGGER.log(Level.FINE, " retrieveLandingStatus for Small runway : is full");
					return false;
				}
			} else {
				return false;
			}
			flightControlDAO.saveFlightDetails(flightDetails);
			return true;
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "IOException encountered in checkLandingStatus for flight : "+flightDetails.getFlightNumber(), e);
			throw new ServiceException(e);
		}catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception encountered in checkLandingStatus for flight : "+flightDetails.getFlightNumber(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<FlightDetails> getLandedFlightDetails() throws ServiceException {
		try {
			LOGGER.log(Level.INFO, " getLandedFlightDetails invoked");
			return flightControlDAO.getAllFlightDetails();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "IOException encountered in getLandedFlightDetails ", e);
			throw new ServiceException(e);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception encountered in getLandedFlightDetails ", e);
			throw new ServiceException(e);
		}
	}
}
