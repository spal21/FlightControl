
package com.nafora.airport.flightcontrol.app;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
@ComponentScan({ "com.nafora.airport.component.flightcontrol","com.nafora.airport.flightcontrol.app.web" })
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("FlightControlApi").select()
				.apis(RequestHandlerSelectors.basePackage("com.nafora.airport.flightcontrol.app.web")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Flight Control Rest API").description("Flight Control Rest API with Swagger. It "
				+ "accepts POST requests with flightNumber and planeType (SMALL|BIG) and:\r\n" + 
				"1. responds with 200 code when plane is accepted for landing\r\n" + 
				"2. responds with 429 code when plane is not accepted\r\n" + 
				"2. keeps tracks of accepted planes and exposes endpoint which lists them")
				.version("1.0").build();
	}
	
	@Bean
	public  PropertyPlaceholderConfigurer propertyPlaceholderConfig(){
		PropertyPlaceholderConfigurer config = new PropertyPlaceholderConfigurer();
		config.setLocation(new ClassPathResource("app.properties") );
		config.setIgnoreUnresolvablePlaceholders(false);
		return config;
	}
	
	@Bean
	  public WebMvcConfigurerAdapter adapter() {
	    return new WebMvcConfigurerAdapter() {
	      @Override
	      public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("swagger-ui.html")
	            .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
	        registry.addResourceHandler("/webjars/**")
	            .addResourceLocations("classpath:/META-INF/resources/webjars/");

	        super.addResourceHandlers(registry);
	      }
	    };
	  }
}
