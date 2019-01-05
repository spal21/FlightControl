######################   FlightControlApi   ###################### 

Rest API that
Provides information about Flight Control.
Validates the Request.
Allows configurable :
	1. number of BIG and Small runways 
	2. landing duration
Documented through Swagger.


How to Run - 
Build - using maven - mvn clean install
Launcher class - com.nafora.airport.flightcontrol.app.Application
API Documentation (Swagger URL) - http://localhost:8082/FlightControl/swagger-ui.html

API Details - 

1.
URL - http://localhost:8082/FlightControl/api/flight/land 
Method - POST
Header - Content-Type:application/json
Payload - 
{
  "flightNumber": "AI12345",
  "flightType": "SMALL"
}

2.
URL - http://localhost:8082/FlightControl/api/flights
Method - GET


Technical Details - 

Technology Used- Java 8, Spring Boot, Spring MVC, Swagger, Maven

Model : 
	2 models used - FlightDetails, FlightType

Architecture - N-layered
	DAO Layer
	Service Layer
	Service Interface(Controller)

Implementation -
	
	DAO Layer - In-memory DAO implementation is done using ConcurrentHashMap.
	
	Services Layer - Facilitates the data to the controller
		
	Service Interface(Controller)- Exposes REST interfaces
	
	Swagger - For documentation of the web service
	
	Framework - SpringBoot
	
	Server - EmbeddedTomcat

Launcher class - com.nafora.flightcontrol.app.Application
API Documentation (Swagger URL) - http://localhost:8082/FlightControl/swagger-ui.html
