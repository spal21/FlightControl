package com.nafora.airport.component.flightcontrol.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nafora.airport.flightcontrol.model.ErrorDetail;
import com.nafora.airport.flightcontrol.model.FlightControlRequest;

@Component
public class FlightControlValidator implements Validator {

	@Override
	public List<ErrorDetail> validate(FlightControlRequest flightControlRequest) {
		List<ErrorDetail> errorList = new ArrayList<>();
		if(flightControlRequest.getFlightNumber()==null){
			ErrorDetail ed1 = new ErrorDetail("FlightNumber", "Must be provided ");
			errorList.add(ed1);
		}
		if(flightControlRequest.getFlightType()==null){
			ErrorDetail ed2 = new ErrorDetail("FlightType", "Must be provided ");
			errorList.add(ed2);
		}
		return errorList;
	}

}
