package com.nafora.airport.flightcontrol.model;

public class ErrorDetail {

	private String field;
	private String description;
	
	public ErrorDetail(String field, String description) {
		super();
		this.field = field;
		this.description = description;
	}
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
