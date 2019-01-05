package com.nafora.airport.component.flightcontrol.exception;

public class ServiceException extends Exception{

	private static final long serialVersionUID = 1853332345156169271L;

	public ServiceException() {
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Throwable throwable) {
		super(throwable);
	}
	
	public ServiceException(String message,Throwable throwable) {
		super(message,throwable);
	}
	
	public ServiceException(String message,Throwable throwable, boolean enableSuppression,boolean writableStackTrace) {
		super(message,throwable,enableSuppression,writableStackTrace);
	}
}
