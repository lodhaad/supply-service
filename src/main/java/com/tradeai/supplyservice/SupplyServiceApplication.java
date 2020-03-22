package com.tradeai.supplyservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.tradeai.supplyservice.helper.SupplyServiceHeper;

@SpringBootApplication
//@EnableDiscoveryClient
public class SupplyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupplyServiceApplication.class, args);
	}
	
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Bean
	public SupplyServiceHeper serviceHelper() {
		return new SupplyServiceHeper();
	}

}
