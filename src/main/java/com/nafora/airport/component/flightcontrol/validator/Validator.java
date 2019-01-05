package com.nafora.airport.component.flightcontrol.validator;

import java.util.List;

import com.nafora.airport.flightcontrol.model.ErrorDetail;
import com.nafora.airport.flightcontrol.model.FlightControlRequest;

public interface Validator {

	List<ErrorDetail> validate(FlightControlRequest flightControlRequest);
}
