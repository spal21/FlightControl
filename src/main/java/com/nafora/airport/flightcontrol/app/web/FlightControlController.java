package com.nafora.airport.flightcontrol.app.web;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nafora.airport.component.flightcontrol.exception.ServiceException;
import com.nafora.airport.component.flightcontrol.services.FlightControlService;
import com.nafora.airport.component.flightcontrol.validator.Validator;
import com.nafora.airport.flightcontrol.model.ErrorDetail;
import com.nafora.airport.flightcontrol.model.FlightControlRequest;
import com.nafora.airport.flightcontrol.model.FlightDetails;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
public class FlightControlController {
	private static final Logger LOGGER = Logger.getLogger(FlightControlController.class.getName());

	@Autowired
	private FlightControlService flightControlService;
	@Autowired
	private Validator validator;
	
	@ApiOperation(value = "check landing status", produces = "application/json",response = FlightDetails.class)
	@RequestMapping(value = { "/api/flight/land" }, method = RequestMethod.POST, headers = {
			"Accept=application/json" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success",response = FlightDetails.class),
			@ApiResponse(code = 429, message = "Not Accepted",response = String.class)})
	public ResponseEntity<?> checkLandingStatus(@RequestBody FlightControlRequest flightInfo) {
		LOGGER.log(Level.INFO, " checkLandingStatus for : "+flightInfo);
		List<ErrorDetail> errors = validator.validate(flightInfo);
		if(errors.size()>0){
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		FlightDetails details = new FlightDetails();
		details.setFlightNumber(flightInfo.getFlightNumber());
		details.setFlightType(flightInfo.getFlightType());
		details.setLandingAcceptedOn(new Date());
		try {
			if(flightControlService.retrieveLandingStatus(details)){
				return new ResponseEntity<>(details, HttpStatus.OK);
			}else{
				LOGGER.log(Level.INFO, " checkLandingStatus rejectted for : "+flightInfo);
				return new ResponseEntity<>(null, HttpStatus.TOO_MANY_REQUESTS);
			}
		} catch (ServiceException e) {
			LOGGER.log(Level.SEVERE, "ServiceException encountered in GetLandingStatus ", e);
			return new ResponseEntity<>("GetLandingStatus ServiceException encountered : "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception encountered in GetLandingStatus ", e);
			return new ResponseEntity<>("GetLandingStatus Exception encountered : "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "get landed flight list", produces = "application/json",response = FlightDetails.class, responseContainer = "list")
	@RequestMapping(value = { "/api/flights" }, method = RequestMethod.GET, headers = { "Accept=application/json" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success")})
	public ResponseEntity<?> getAllFlights() {
		try {
			LOGGER.log(Level.INFO, " getAllFlights invoked.");
			return new ResponseEntity<>(flightControlService.getLandedFlightDetails(), HttpStatus.OK);
		} catch (ServiceException e) {
			LOGGER.log(Level.SEVERE, "ServiceException encountered in getAllFlights ", e);
			return new ResponseEntity<>(" GetAllFlights ServiceException encountered : "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception encountered in getAllFlights ", e);
			return new ResponseEntity<>(" GetAllFlights Exception encountered : "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}